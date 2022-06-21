package com.temesgenbesha.projectmanagementsystem.exception;

import com.temesgenbesha.projectmanagementsystem.dto.RegisterUserDTO;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(RegisterUserDTO user) {
        super("User with username %s already exists!".formatted(user.getUsername()));
    }
}
