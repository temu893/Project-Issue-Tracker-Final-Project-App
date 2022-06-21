package com.temesgenbesha.projectmanagementsystem.exception;

public class RoleNotFoundException extends Exception {
    public RoleNotFoundException(Long projectId) {
        super("No Roles found with This Role name Exists".formatted(projectId));
    }

}
