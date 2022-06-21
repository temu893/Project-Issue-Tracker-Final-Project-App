package com.temesgenbesha.projectmanagementsystem.service;

import com.temesgenbesha.projectmanagementsystem.dto.IssueDTO;
import com.temesgenbesha.projectmanagementsystem.entity.Issue;
import com.temesgenbesha.projectmanagementsystem.entity.Project;
import com.temesgenbesha.projectmanagementsystem.entity.User;
import com.temesgenbesha.projectmanagementsystem.exception.ProjectNotFoundException;
import com.temesgenbesha.projectmanagementsystem.repository.IssueRepository;
import com.temesgenbesha.projectmanagementsystem.repository.ProjectRepository;
import com.temesgenbesha.projectmanagementsystem.repository.UserRepository;
import com.temesgenbesha.projectmanagementsystem.security.CustomUserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class IssueServiceImpl implements IssueService {

   private final UserRepository userRepository;

    private final IssueRepository issueRepository;
    private final ProjectRepository projectRepository;



    @Override
    public List<Issue> getIssuesFromProject(Long projectId) throws ProjectNotFoundException {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        List<Issue> issues = issueRepository.findAllByProject(project);
        return issues;
    }

    @Override
    public List<Issue> getIssueFromProjectByAssignedToAndCreatedBy(Long projectId) throws ProjectNotFoundException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException(projectId));
        User userData = ((CustomUserPrincipal) principal).getUser();
        //List<Issue> issues = issueRepository.findAllByProjectAndAssignedToOrCreatedBy(project,userData,userData);
        List<Issue> issues = new ArrayList<>();
        List<Issue> issuesAssignTo = issueRepository.findAllByProjectAndAssignedTo(project,userData);
        List<Issue> issuesCreatedBy = issueRepository.findAllByProjectAndCreatedBy(project,userData);
        if(issuesAssignTo.size()>0){
            for(int i=0;i<issuesAssignTo.size();i++) {
                issues.add(issuesAssignTo.get(i));
            }
        }
        if(issuesCreatedBy.size()>0){
            for(int t=0;t<issuesCreatedBy.size();t++) {
                issues.add(issuesCreatedBy.get(t));
            }
        }

        return issues;
    }

    @Override
    public Issue getIssue(Long issueId) {
        return issueRepository.getById(issueId);
    }

    @Override
    public Issue updateIssue(Long issueId, IssueDTO issueDTO) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        StringBuilder targetResolutionDate = new StringBuilder(String.valueOf(issueDTO.getTargetResolutionDate()));
        targetResolutionDate.setCharAt(10, ' ');
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(targetResolutionDate, formatter);
        Issue issue = issueRepository.getById(issueId);
        issue.setSummary(issueDTO.getSummary());
        issue.setDescription(issueDTO.getDescription());
        issue.setResolutionSummary(issueDTO.getResolutionSummary());
        issue.setAssignedTo(userRepository.findById(Long.parseLong(issueDTO.getAssignedTo())).get());
        issue.setAssignedOn(LocalDateTime.now());
        issue.setStatus(issueDTO.getStatus());
        issue.setModifiedBy(((CustomUserPrincipal) principal).getUser());
        issue.setModifiedOn(LocalDateTime.now());
        issue.setPriority(issueDTO.getPriority());
        issue.setTargetResolutionDate(dateTime);
        issue.setResolutionSummary(issueDTO.getResolutionSummary());
        System.out.printf("project ID: " + issueDTO.getProjectID());
        issue = issueRepository.save(issue);
        log.info("Updated issue ", issue);
        return issue;
    }

    @Override
    public Issue addIssue(IssueDTO issueDTO) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User admin = userRepository.findByUsername("temub").orElseThrow(() -> new UsernameNotFoundException("User with username temub not found!"));
        User user1 = userRepository.findByUsername("mm").orElseThrow(() -> new UsernameNotFoundException("User with username mm not found!"));
        System.out.print(issueDTO.getTargetResolutionDate());
        System.out.print(issueDTO.getAssignedTo());
        StringBuilder targetResolutionDate = new StringBuilder(String.valueOf(issueDTO.getTargetResolutionDate()));
        targetResolutionDate.setCharAt(10, ' ');
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(targetResolutionDate, formatter);
        Issue issue = new Issue();
        issue.setSummary(issueDTO.getSummary());
        issue.setDescription(issueDTO.getDescription());
        issue.setCreatedBy(((CustomUserPrincipal) principal).getUser());
        issue.setCreatedOn(LocalDateTime.now());
        issue.setAssignedTo(userRepository.findById(Long.parseLong(issueDTO.getAssignedTo())).get());
        issue.setAssignedOn(LocalDateTime.now());
        issue.setStatus(issueDTO.getStatus());
        issue.setPriority(issueDTO.getPriority());
        issue.setTargetResolutionDate(dateTime);
        issue.setResolutionSummary(issueDTO.getResolutionSummary());
        System.out.printf("project ID: " + issueDTO.getProjectID());
        issue.setProject(projectRepository.getById(Long.parseLong(issueDTO.getProjectID())));
        issue = issueRepository.save(issue);
        log.info("Created new issue ", issue);
        return issue;

    }

    @Override
    public void deleteIssue(Long id) {
        issueRepository.deleteById(id);

    }
}
