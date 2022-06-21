package com.temesgenbesha.projectmanagementsystem.exception;

public class ProjectNotFoundException extends Exception {
    public ProjectNotFoundException(Long projectId) {
        super("No project found with id: %d".formatted(projectId));
    }
}
