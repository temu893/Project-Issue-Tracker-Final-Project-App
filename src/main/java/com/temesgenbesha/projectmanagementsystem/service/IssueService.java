package com.temesgenbesha.projectmanagementsystem.service;

import com.temesgenbesha.projectmanagementsystem.dto.IssueDTO;
import com.temesgenbesha.projectmanagementsystem.entity.Issue;
import com.temesgenbesha.projectmanagementsystem.exception.ProjectNotFoundException;

import java.util.List;

public interface IssueService {

    List<Issue> getIssuesFromProject(Long projectId) throws ProjectNotFoundException;

    List<Issue> getIssueFromProjectByAssignedToAndCreatedBy(Long projectId) throws ProjectNotFoundException;

    Issue getIssue(Long issueId);

    Issue updateIssue(Long issueId, IssueDTO issueDTO);

    Issue addIssue(IssueDTO issueDTO);


    void deleteIssue(Long id);
}
