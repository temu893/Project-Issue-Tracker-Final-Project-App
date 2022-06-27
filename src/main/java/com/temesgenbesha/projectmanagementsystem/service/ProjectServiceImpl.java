package com.temesgenbesha.projectmanagementsystem.service;

import com.temesgenbesha.projectmanagementsystem.dto.ProjectDTO;
import com.temesgenbesha.projectmanagementsystem.entity.Issue;
import com.temesgenbesha.projectmanagementsystem.entity.Project;
import com.temesgenbesha.projectmanagementsystem.entity.User;
import com.temesgenbesha.projectmanagementsystem.exception.ProjectNotFoundException;
import com.temesgenbesha.projectmanagementsystem.repository.ProjectRepository;
import com.temesgenbesha.projectmanagementsystem.repository.UserRepository;
import com.temesgenbesha.projectmanagementsystem.security.CustomUserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



//for all service class we have to use @service annotation otherWise spring will not recognize this class as a service layer
//@RequiredArgsConstructor we have to annotate this since we are using lombok

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectServiceImpl implements ProjectService {

    private final UserRepository userRepository;

    private final ProjectRepository projectRepository;
    @Autowired
    private final IssueService issueService;

    private final AuthenticationService authenticationService;

    @Override
    public List<ProjectDTO> getAllProjects() {
        //return projectRepository.findAll(); // this will return all project in the list
        return projectRepository.findAll().stream().map(Project::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<Project> getAllProjectsByCreatedUser() throws ProjectNotFoundException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userData = ((CustomUserPrincipal) principal).getUser();
        List<Project> projects = projectRepository.findAll();
        List<Project> result = new ArrayList<>();
//        if the project created and the user id and createdBy id is equal the project will assign under createdby id
        if(projects.size()>0) {
            for (int i = 0; i < projects.size(); i++) {
                if (projects.get(i).getCreatedBy().getId()==userData.getId()){
                    result.add(projects.get(i));
                }else {
                    // if the issue is assigne to the user id the project also will assign under the assign id
                    List<Issue> issues = issueService.getIssueFromProjectByAssignedToAndCreatedBy(projects.get(i).getId());
                    if (issues.size() > 0) {
                        result.add(projects.get(i));
                    }
                }
            }
        }
        return result;
    }

    @Override
    public ProjectDTO getProjectById(Long id) throws Exception {
//        Optional<ProjectDto> projectOptional = projectRepository.findById(id);
//        if(projectOptional.isEmpty()) throw new Exception();
//        return projectOptional.get();

//
        Project project = projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException(id));
        return project.toDTO();
    }

    @Override
    public ProjectDTO addProject(ProjectDTO projectDTO) {
        Project project = projectDTO.toEntity();
        project.setCreatedBy(authenticationService.getUserInfo().toEntity());
        project.setCreatedOn(LocalDateTime.now());

        project = projectRepository.save(project);

        //logging will help what is going on in each step of this application // and for troubleshooting
        log.info("Created new project: {}", project);

        return project.toDTO();
    }

    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public ProjectDTO updateProject(Long id, ProjectDTO projectDTO) throws ProjectNotFoundException {
        Project project = projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException(id));

        project.setName(projectDTO.getName());
        project.setProjectDescription(projectDTO.getProjectDescription());

        project.setActualEndDate(projectDTO.getActualEndDate());
        project.setModifiedBy(authenticationService.getUserInfo().toEntity());
        project.setModifiedOn(LocalDateTime.now());

        project = projectRepository.save(project);
        log.info("Project updated: {}", project);

        return project.toDTO();
    }
}
