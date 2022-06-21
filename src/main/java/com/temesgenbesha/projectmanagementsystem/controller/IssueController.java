
package com.temesgenbesha.projectmanagementsystem.controller;

import com.temesgenbesha.projectmanagementsystem.dto.IssueDTO;
import com.temesgenbesha.projectmanagementsystem.entity.Issue;
import com.temesgenbesha.projectmanagementsystem.entity.Status;
import com.temesgenbesha.projectmanagementsystem.service.IssueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/issue")
public class IssueController {
    @InitBinder
    public void initBinder( WebDataBinder binder )
    {
        binder.registerCustomEditor( LocalDateTime.class, new PropertyEditorSupport()
        {
            @Override
            public void setAsText( String text ) throws IllegalArgumentException
            {
                //2022-06-03T00:57
                //"2019-09-20T12:36:39.359"
                System.out.println("Get time: "+text);
                LocalDateTime.parse( text, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm") );
            }
        } );
    }

    private final IssueService issueService;


    @RequestMapping
    public String displayEnums(Model model){
        model.addAttribute("status", Status.values());

        return "issue/new" ;

    }

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


}
