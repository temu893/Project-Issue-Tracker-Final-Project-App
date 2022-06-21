package com.temesgenbesha.projectmanagementsystem.dto;

import lombok.Data;

@Data
public class RegisterUserDTO {
    private String username;
    private String password;
    private String name;
    private String email;
}
