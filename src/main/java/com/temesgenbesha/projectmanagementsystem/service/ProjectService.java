package com.temesgenbesha.projectmanagementsystem.service;

import com.temesgenbesha.projectmanagementsystem.dto.ProjectDTO;
import com.temesgenbesha.projectmanagementsystem.entity.Project;
import com.temesgenbesha.projectmanagementsystem.exception.ProjectNotFoundException;

import java.util.List;

public interface ProjectService {
    List<ProjectDTO> getAllProjects();

    List<Project> getAllProjectsByCreatedUser() throws ProjectNotFoundException;


    ProjectDTO getProjectById(Long id) throws Exception;

    ProjectDTO addProject(ProjectDTO projectDTO);

    void deleteProject(Long id);


    ProjectDTO updateProject(Long id, ProjectDTO projectDTO) throws ProjectNotFoundException;
}
