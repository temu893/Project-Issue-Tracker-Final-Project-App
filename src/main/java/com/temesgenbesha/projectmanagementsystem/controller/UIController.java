package com.temesgenbesha.projectmanagementsystem.controller;

import com.temesgenbesha.projectmanagementsystem.dto.IssueDTO;
import com.temesgenbesha.projectmanagementsystem.dto.ProjectDTO;
import com.temesgenbesha.projectmanagementsystem.dto.RegisterUserDTO;
import com.temesgenbesha.projectmanagementsystem.exception.ProjectNotFoundException;
import com.temesgenbesha.projectmanagementsystem.service.IssueService;
import com.temesgenbesha.projectmanagementsystem.service.ProjectService;
import com.temesgenbesha.projectmanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@RequiredArgsConstructor
public class UIController {

    private final ProjectService projectService;
    private final IssueService issueService;
    private final UserService userService;

    @GetMapping
    public String index() {
        return "redirect:/overview";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";

    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new RegisterUserDTO());

        return "register";
    }

    @GetMapping("/overview")
    public String getProjectOverviewPage(Model model) throws ProjectNotFoundException {
        //returning list of project
        model.addAttribute("projects", projectService.getAllProjectsByCreatedUser());

        return "projectOverview";
    }

    //return single project by the id
    @GetMapping("/project/{id}")
    public String getProjectDetailPage(Model model, @PathVariable Long id) throws Exception {
        model.addAttribute("project", projectService.getProjectById(id));

        return "projectDetail";
    }

    @GetMapping("/project/{id}/update")
    public String showUpdateProject(Model model, @PathVariable Long id) throws Exception {
        model.addAttribute("project", projectService.getProjectById(id));
        return "updateProject";
    }


    @GetMapping("/project/{id}/issue")
    public String getIssueFromSpecificProject(Model model, @PathVariable Long id) {
        try {
            model.addAttribute("projectID", id);
            model.addAttribute("issues", issueService.getIssueFromProjectByAssignedToAndCreatedBy(id));
        } catch (ProjectNotFoundException e) {
            return "redirect:/project/" + id + "/issue?error";
        }

        return "issueOverview";
    }

    @GetMapping("/project/new")
    public String createNewProject(Model model) {
        model.addAttribute("project", new ProjectDTO());

        return "createNewProject";
    }

    @GetMapping("/issue/{projectId}/new")
    public String createNewIssue(@PathVariable("projectId") String projectID, Model model) {
        model.addAttribute("issue", new IssueDTO());
        model.addAttribute("projectID", projectID);
        model.addAttribute("users", userService.getAllUsers());

        return "createNewIssue";
    }

    @GetMapping("/issue/{issueId}/update")
    public String updateIssue(@PathVariable("issueId") String issueID, Model model) {
        model.addAttribute("issue", issueService.getIssue(Long.parseLong(issueID)));
        model.addAttribute("users", userService.getAllUsers());

        return "updateIssue";
    }




//    @GetMapping
//    public String getIssueDetailPage(Model model,@PathVariable Long id) throws Exception{
//        model.addAttribute("issue",issueService.getIssueById(id));
//        return "issueDetail";
//
//    }

}
