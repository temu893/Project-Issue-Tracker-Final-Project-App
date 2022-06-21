package com.temesgenbesha.projectmanagementsystem.exception;

public class IssueNotFoundException extends Exception{
    public IssueNotFoundException(Long issueId) {
        super("No Issues found with id: %d".formatted(issueId));
    }

}
