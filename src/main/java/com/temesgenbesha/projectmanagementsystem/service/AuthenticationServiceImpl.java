package com.temesgenbesha.projectmanagementsystem.service;

import com.temesgenbesha.projectmanagementsystem.dto.RegisterUserDTO;
import com.temesgenbesha.projectmanagementsystem.dto.UserDTO;
import com.temesgenbesha.projectmanagementsystem.entity.User;
import com.temesgenbesha.projectmanagementsystem.exception.UserAlreadyExistsException;
import com.temesgenbesha.projectmanagementsystem.repository.RoleRepository;
import com.temesgenbesha.projectmanagementsystem.repository.UserRepository;
import com.temesgenbesha.projectmanagementsystem.security.CustomUserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public void logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
        log.info("Logged out current user!");
    }

    @Override
    public UserDTO registerUser(RegisterUserDTO registerUserDTO) throws UserAlreadyExistsException {
        // check if user with same username already exists, throw an exception if so
        Optional<User> userOptional = userRepository.findByUsername(registerUserDTO.getUsername());

        if (userOptional.isPresent()) {
            throw new UserAlreadyExistsException(registerUserDTO);
        }

        User user = new User();
        user.setUsername(registerUserDTO.getUsername());
        user.setName(registerUserDTO.getName());
        user.setEmail(registerUserDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerUserDTO.getPassword()));
        user.setRoles(Set.of(roleRepository.findByName("ROLE_USER")));
        user.setAssignedProjects(new HashSet<>());
        user.setCreatedOn(ZonedDateTime.now());

        user = userRepository.save(user);
        log.error("Registered user: {}", user);

        return user.toDTO();
    }

    @Override
    public UserDTO getUserInfo() {
        CustomUserPrincipal currentPrincipal = (CustomUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return currentPrincipal.getUser().toDTO();
    }
}
