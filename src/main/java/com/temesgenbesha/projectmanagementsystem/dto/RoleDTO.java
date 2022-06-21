package com.temesgenbesha.projectmanagementsystem.dto;

import com.temesgenbesha.projectmanagementsystem.entity.Role;
import lombok.Data;

@Data
public class RoleDTO {
    private Long id;
    private String name;

    // The Mapper implemented inside the DTO
    // This method will return Role entity
    public Role toEntity() {
        Role role = new Role();
        role.setId(id);
        role.setName(name);

        return role;
    }
}
