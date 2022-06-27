package com.temesgenbesha.projectmanagementsystem.dto;

import com.temesgenbesha.projectmanagementsystem.entity.Issue;
import com.temesgenbesha.projectmanagementsystem.entity.Priority;
import com.temesgenbesha.projectmanagementsystem.entity.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IssueDTO {
    private Long id;
    private String summary;
    private String description;
    private String comment;
    private String createdBy;
    private LocalDateTime createdOn;
    private String assignedTo;
    private LocalDateTime assignedOn;
    private Status status;
    private Priority priority;
    private String targetResolutionDate;
    private String resolutionSummary;
    private String projectID;

}
