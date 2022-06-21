package com.temesgenbesha.projectmanagementsystem.service;

import com.temesgenbesha.projectmanagementsystem.dto.RegisterUserDTO;
import com.temesgenbesha.projectmanagementsystem.dto.UserDTO;
import com.temesgenbesha.projectmanagementsystem.exception.UserAlreadyExistsException;

public interface AuthenticationService {
    void logout();

    UserDTO registerUser(RegisterUserDTO registerUserDTO) throws UserAlreadyExistsException;

    UserDTO getUserInfo();
}
