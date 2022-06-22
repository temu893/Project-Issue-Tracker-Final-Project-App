package com.temesgenbesha.projectmanagementsystem.controller;

import com.temesgenbesha.projectmanagementsystem.dto.RegisterUserDTO;
import com.temesgenbesha.projectmanagementsystem.exception.UserAlreadyExistsException;
import com.temesgenbesha.projectmanagementsystem.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public void registerUser(@ModelAttribute("user") RegisterUserDTO registerUserDTO, HttpServletResponse response) throws IOException, UserAlreadyExistsException {
        authenticationService.registerUser(registerUserDTO);

        response.sendRedirect("/login?registered");
    }

    @PostMapping("/contact")
    public void contactForm(HttpServletResponse response) throws IOException {

        response.sendRedirect("/login?contacted");
    }

    @GetMapping("/logout")
    public void logoutUser(HttpServletResponse response) throws IOException {
        authenticationService.logout();

        response.sendRedirect("/login?logout");
    }
}
