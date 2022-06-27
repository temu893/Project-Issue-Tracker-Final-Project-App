package com.temesgenbesha.projectmanagementsystem.controller;

import com.temesgenbesha.projectmanagementsystem.dto.ProjectDTO;
import com.temesgenbesha.projectmanagementsystem.exception.ProjectNotFoundException;
import com.temesgenbesha.projectmanagementsystem.service.IssueService;
import com.temesgenbesha.projectmanagementsystem.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/project")
public class ProjectController {
    private final ProjectService projectService;
    private final IssueService issueService;

    @PostMapping
    public void saveNewProject(@ModelAttribute("project") ProjectDTO projectDTO, HttpServletResponse response) throws IOException {
        projectService.addProject(projectDTO);
        response.sendRedirect("/overview?success");
    }

    @PostMapping("/{id}")

    public void updateProject(@PathVariable Long id, @ModelAttribute("project") ProjectDTO projectDTO, HttpServletResponse response) throws ProjectNotFoundException, IOException {
        projectService.updateProject(id, projectDTO);
        response.sendRedirect("/overview?updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) throws Exception {
        projectService.deleteProject(id);
        return ResponseEntity.ok().build();
    }

//
//    @RequestMapping(value =  "/api/issue")
//    public String displayEnums(Model model){
//        model.addAttribute("status", Status.values());
//
//        return "issue/new" ;
//
//    }
//
//    @PostMapping(value = "/api/issue")
//    public ResponseEntity<IssueDTO> createNewIssue(@ModelAttribute("issue") IssueDTO issueDTO) throws URISyntaxException{
//        IssueDTO createdIssue = issueService.addIssue(issueDTO);
//        return ResponseEntity.created(new URI("/api/issue/" + createdIssue.getId())).build();
//    }


}
