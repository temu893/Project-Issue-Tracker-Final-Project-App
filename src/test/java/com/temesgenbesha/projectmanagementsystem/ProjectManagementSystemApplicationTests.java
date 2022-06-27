package com.temesgenbesha.projectmanagementsystem;

import com.temesgenbesha.projectmanagementsystem.entity.Project;
import com.temesgenbesha.projectmanagementsystem.entity.Role;
import com.temesgenbesha.projectmanagementsystem.entity.User;
import com.temesgenbesha.projectmanagementsystem.repository.ProjectRepository;
import com.temesgenbesha.projectmanagementsystem.repository.RoleRepository;
import com.temesgenbesha.projectmanagementsystem.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ProjectManagementSystemApplicationTests {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    //@Test
//    public void testAdd(){
//    Project project = new Project();
//    User user  = new User();
//    user.setUsername("Abele");
//    user.setAssignedProjects(Collections.singleton(projectRepository.getById(1L)));
//    user.setEmail("abebe@nn");
//    user.setCreatedOn(ZonedDateTime.now());
//    user.setPassword("pass");
//    user.setRoles(Collections.singleton(roleRepository.findByName("User")));
//    user.setName("Abe");
//    project.setId(1L);
//    project.setProjectDescription("enforcer");
//    project.setCreatedBy(userRepository.getById(3L));
//    project.setName("name");
//    project.setModifiedOn(LocalDateTime.now().plusDays(2));
//    project.setModifiedBy(userRepository.getById(3L));
//    project.setActualEndDate(LocalDateTime.now().plusWeeks(1));
//    project.setCreatedOn(LocalDateTime.now());
//    project.setTargetEndDate(LocalDateTime.now().plusWeeks(2));
//    project.setStartDate(LocalDateTime.now());
//    projectRepository.save(project);
//    assertNotNull(projectRepository.findById(1L).get());
//    }
}
