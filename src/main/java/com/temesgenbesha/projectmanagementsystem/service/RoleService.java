package com.temesgenbesha.projectmanagementsystem.service;

import com.temesgenbesha.projectmanagementsystem.dto.RoleDTO;

import java.util.List;

public interface RoleService {
    List<RoleDTO> getAllIssues();
    RoleDTO getRoleById(long id);
    RoleDTO addRole(RoleDTO roleDTO);
}
