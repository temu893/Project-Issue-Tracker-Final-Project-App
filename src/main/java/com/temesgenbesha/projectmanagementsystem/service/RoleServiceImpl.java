package com.temesgenbesha.projectmanagementsystem.service;

import com.temesgenbesha.projectmanagementsystem.dto.RoleDTO;
import com.temesgenbesha.projectmanagementsystem.repository.RoleRepository;
import com.temesgenbesha.projectmanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Override
    public List<RoleDTO> getAllIssues() {
        return null;
    }

    @Override
    public RoleDTO getRoleById(long id) {
        //Role role = roleRepository.findById(id).orElseThrow(() -> new RoleNotFoundException(id));
        //return role.toDTO();
        return null;
    }

    @Override
    public RoleDTO addRole(RoleDTO roleDTO) {
        return null;
    }
}
