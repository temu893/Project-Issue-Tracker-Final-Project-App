
package com.temesgenbesha.projectmanagementsystem.controller;

import com.temesgenbesha.projectmanagementsystem.dto.IssueDTO;
import com.temesgenbesha.projectmanagementsystem.entity.Issue;
import com.temesgenbesha.projectmanagementsystem.service.IssueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/issue")
public class IssueController {

    private final IssueService issueService;




    @PostMapping
    public String createNewIssue(@ModelAttribute("issue") IssueDTO issueDTO) throws URISyntaxException {
        Issue createdIssue = issueService.addIssue(issueDTO);
        return "redirect:/project/"+issueDTO.getProjectID()+"/issue";
    }


    @PostMapping(value = "/update/{id}")
    public String updateIssue(@PathVariable("id")String issueID, @ModelAttribute("issue") IssueDTO issueDTO) throws URISyntaxException {
        Issue updatedIssue = issueService.updateIssue(Long.parseLong(issueID),issueDTO);
        return "redirect:/project/"+updatedIssue.getProject().getId()+"/issue";
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deleteIssue(@PathVariable("id") Long id) {
        issueService.deleteIssue(id);
        return ResponseEntity.ok().build();
    }

//    @PostMapping("(/{id}")
//    public ResponseEntity<Void> deleteIssue(@PathVariable Long id) {
//        issueService.deleteIssue(id);
//        return ResponseEntity.ok().build();
//    }

}
