package com.temesgenbesha.projectmanagementsystem.dto;

import com.temesgenbesha.projectmanagementsystem.entity.User;
import lombok.Data;

import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UserDTO {
    private Long id;
    private String name;
    private String username;
    private String email;
    private Set<ProjectDTO> assignedProjects;
    private Set<RoleDTO> roles;


    // The Mapper implemented inside the DTO
    // This method will return User entity
    public User toEntity() {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setUsername(username);
        user.setEmail(email);
        // Set of project entity
        // convert project Dto to project entity
        if (assignedProjects != null) {
            //for each ProjectDTO Stream() will call toEntity method on it, and collect will collect all Issue entity's and
            // create a set of it
            user.setAssignedProjects(assignedProjects.stream().map(ProjectDTO::toEntity).collect(Collectors.toSet()));
        }
        //set of role entity
        if (roles != null) {
            //for each RoleDTO Stream() will call toEntity method on it, and collect will collect all Issue entity's and
            // create a set of it
            user.setRoles(roles.stream().map(RoleDTO::toEntity).collect(Collectors.toSet()));
        }

        return user;
    }
}
