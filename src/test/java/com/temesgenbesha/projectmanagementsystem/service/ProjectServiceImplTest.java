package com.temesgenbesha.projectmanagementsystem.service;

import com.temesgenbesha.projectmanagementsystem.dto.ProjectDTO;
import com.temesgenbesha.projectmanagementsystem.dto.RoleDTO;
import com.temesgenbesha.projectmanagementsystem.dto.UserDTO;
import com.temesgenbesha.projectmanagementsystem.entity.Issue;
import com.temesgenbesha.projectmanagementsystem.entity.Project;
import com.temesgenbesha.projectmanagementsystem.entity.User;
import com.temesgenbesha.projectmanagementsystem.exception.ProjectNotFoundException;
import com.temesgenbesha.projectmanagementsystem.repository.ProjectRepository;
import com.temesgenbesha.projectmanagementsystem.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTest {

    @Mock
    private UserRepository mockUserRepository;
    @Mock
    private ProjectRepository mockProjectRepository;
    @Mock
    private IssueService mockIssueService;
    @Mock
    private AuthenticationService mockAuthenticationService;

    private ProjectServiceImpl projectServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        projectServiceImplUnderTest = new ProjectServiceImpl(mockUserRepository, mockProjectRepository,
                mockIssueService, mockAuthenticationService);
    }


    @Test
    void testGetAllProjects_ProjectRepositoryReturnsNoItems() {
        // Setup
        when(mockProjectRepository.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<ProjectDTO> result = projectServiceImplUnderTest.getAllProjects();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }
    @Test
    void testUpdateProject_ProjectRepositoryFindByIdReturnsAbsent() {
        // Setup
        final ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(0L);
        projectDTO.setName("name");
        projectDTO.setProjectDescription("projectDescription");
        projectDTO.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        projectDTO.setTargetEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        projectDTO.setActualEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        projectDTO.setCreatedOn(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        final UserDTO createdBy = new UserDTO();
        createdBy.setId(0L);
        createdBy.setName("name");
        createdBy.setUsername("username");
        createdBy.setEmail("email");
        createdBy.setAssignedProjects(Set.of(new ProjectDTO()));
        final RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(0L);
        createdBy.setRoles(Set.of(roleDTO));
        projectDTO.setCreatedBy(createdBy);

        when(mockProjectRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> projectServiceImplUnderTest.updateProject(0L, projectDTO))
                .isInstanceOf(ProjectNotFoundException.class);
    }
    @Test
    void testGetProjectById_ProjectRepositoryReturnsAbsent() {
        // Setup
        when(mockProjectRepository.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> projectServiceImplUnderTest.getProjectById(0L))
                .isInstanceOf(ProjectNotFoundException.class);
    }
    @Test
    void testDeleteProject() {
        // Setup
        // Run the test
        projectServiceImplUnderTest.deleteProject(0L);

        // Verify the results
        verify(mockProjectRepository).deleteById(0L);
    }

//
//    @Test
//    void testGetAllProjectsByCreatedUser() throws Exception {
//        // Setup
//        final Project project = new Project();
//        project.setId(0L);
//        project.setName("name");
//        project.setProjectDescription("projectDescription");
//        project.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        project.setTargetEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        project.setActualEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        project.setCreatedOn(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        final User createdBy = new User();
//        createdBy.setId(0L);
//        createdBy.setName("name");
//        createdBy.setEmail("email");
//        createdBy.setUsername("username");
//        project.setCreatedBy(createdBy);
//        project.setModifiedOn(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        final User modifiedBy = new User();
//        modifiedBy.setId(0L);
//        modifiedBy.setName("name");
//        modifiedBy.setEmail("email");
//        modifiedBy.setUsername("username");
//        project.setModifiedBy(modifiedBy);
//        final List<Project> expectedResult = List.of(project);
//
//        // Configure ProjectRepository.findAll(...).
//        final Project project1 = new Project();
//        project1.setId(0L);
//        project1.setName("name");
//        project1.setProjectDescription("projectDescription");
//        project1.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        project1.setTargetEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        project1.setActualEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        project1.setCreatedOn(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        final User createdBy1 = new User();
//        createdBy1.setId(0L);
//        createdBy1.setName("name");
//        createdBy1.setEmail("email");
//        createdBy1.setUsername("username");
//        project1.setCreatedBy(createdBy1);
//        project1.setModifiedOn(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        final User modifiedBy1 = new User();
//        modifiedBy1.setId(0L);
//        modifiedBy1.setName("name");
//        modifiedBy1.setEmail("email");
//        modifiedBy1.setUsername("username");
//        project1.setModifiedBy(modifiedBy1);
//        final List<Project> projects = List.of(project1);
//        when(mockProjectRepository.findAll()).thenReturn(projects);
//
//        // Configure IssueService.getIssueFromProjectByAssignedToAndCreatedBy(...).
//        final Issue issue = new Issue();
//        issue.setId(0L);
//        issue.setSummary("summary");
//        issue.setDescription("description");
//        issue.setComment("comment");
//        final User createdBy2 = new User();
//        createdBy2.setId(0L);
//        createdBy2.setName("name");
//        createdBy2.setEmail("email");
//        createdBy2.setUsername("username");
//        createdBy2.setPassword("password");
//        createdBy2.setCreatedOn(ZonedDateTime.of(LocalDateTime.of(2020, 1, 1, 0, 0, 0), ZoneOffset.UTC));
//        createdBy2.setModifiedOn(ZonedDateTime.of(LocalDateTime.of(2020, 1, 1, 0, 0, 0), ZoneOffset.UTC));
//        final Project project2 = new Project();
//        project2.setId(0L);
//        project2.setName("name");
//        createdBy2.setAssignedProjects(Set.of(project2));
//        issue.setCreatedBy(createdBy2);
//        final List<Issue> issues = List.of(issue);
//        when(mockIssueService.getIssueFromProjectByAssignedToAndCreatedBy(0L)).thenReturn(issues);
//
//        // Run the test
//        final List<Project> result = projectServiceImplUnderTest.getAllProjectsByCreatedUser();
//
//        // Verify the results
//        assertThat(result).isEqualTo(expectedResult);
//    }

//    @Test
//    void testGetAllProjectsByCreatedUser_ProjectRepositoryReturnsNoItems() throws Exception {
//        // Setup
//        when(mockProjectRepository.findAll()).thenReturn(Collections.emptyList());
//
//        // Configure IssueService.getIssueFromProjectByAssignedToAndCreatedBy(...).
//        final Issue issue = new Issue();
//        issue.setId(0L);
//        issue.setSummary("summary");
//        issue.setDescription("description");
//        issue.setComment("comment");
//        final User createdBy = new User();
//        createdBy.setId(0L);
//        createdBy.setName("name");
//        createdBy.setEmail("email");
//        createdBy.setUsername("username");
//        createdBy.setPassword("password");
//        createdBy.setCreatedOn(ZonedDateTime.of(LocalDateTime.of(2020, 1, 1, 0, 0, 0), ZoneOffset.UTC));
//        createdBy.setModifiedOn(ZonedDateTime.of(LocalDateTime.of(2020, 1, 1, 0, 0, 0), ZoneOffset.UTC));
//        final Project project = new Project();
//        project.setId(0L);
//        project.setName("name");
//        createdBy.setAssignedProjects(Set.of(project));
//        issue.setCreatedBy(createdBy);
//        final List<Issue> issues = List.of(issue);
//        when(mockIssueService.getIssueFromProjectByAssignedToAndCreatedBy(0L)).thenReturn(issues);
//
//        // Run the test
//        final List<Project> result = projectServiceImplUnderTest.getAllProjectsByCreatedUser();
//
//        // Verify the results
//        assertThat(result).isEqualTo(Collections.emptyList());
//    }

//    @Test
//    void testGetAllProjectsByCreatedUser_IssueServiceReturnsNoItems() throws Exception {
//        // Setup
//        final Project project = new Project();
//        project.setId(0L);
//        project.setName("name");
//        project.setProjectDescription("projectDescription");
//        project.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        project.setTargetEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        project.setActualEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        project.setCreatedOn(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        final User createdBy = new User();
//        createdBy.setId(0L);
//        createdBy.setName("name");
//        createdBy.setEmail("email");
//        createdBy.setUsername("username");
//        project.setCreatedBy(createdBy);
//        project.setModifiedOn(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        final User modifiedBy = new User();
//        modifiedBy.setId(0L);
//        modifiedBy.setName("name");
//        modifiedBy.setEmail("email");
//        modifiedBy.setUsername("username");
//        project.setModifiedBy(modifiedBy);
//        final List<Project> expectedResult = List.of(project);
//
//        // Configure ProjectRepository.findAll(...).
//        final Project project1 = new Project();
//        project1.setId(0L);
//        project1.setName("name");
//        project1.setProjectDescription("projectDescription");
//        project1.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        project1.setTargetEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        project1.setActualEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        project1.setCreatedOn(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        final User createdBy1 = new User();
//        createdBy1.setId(0L);
//        createdBy1.setName("name");
//        createdBy1.setEmail("email");
//        createdBy1.setUsername("username");
//        project1.setCreatedBy(createdBy1);
//        project1.setModifiedOn(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        final User modifiedBy1 = new User();
//        modifiedBy1.setId(0L);
//        modifiedBy1.setName("name");
//        modifiedBy1.setEmail("email");
//        modifiedBy1.setUsername("username");
//        project1.setModifiedBy(modifiedBy1);
//        final List<Project> projects = List.of(project1);
//        when(mockProjectRepository.findAll()).thenReturn(projects);
//
//        when(mockIssueService.getIssueFromProjectByAssignedToAndCreatedBy(0L)).thenReturn(Collections.emptyList());
//
//        // Run the test
//        final List<Project> result = projectServiceImplUnderTest.getAllProjectsByCreatedUser();
//
//        // Verify the results
//        assertThat(result).isEqualTo(expectedResult);
//    }

//    @Test
//    void testGetAllProjectsByCreatedUser_IssueServiceThrowsProjectNotFoundException() throws Exception {
//        // Setup
//        // Configure ProjectRepository.findAll(...).
//        final Project project = new Project();
//        project.setId(0L);
//        project.setName("name");
//        project.setProjectDescription("projectDescription");
//        project.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        project.setTargetEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        project.setActualEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        project.setCreatedOn(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        final User createdBy = new User();
//        createdBy.setId(0L);
//        createdBy.setName("name");
//        createdBy.setEmail("email");
//        createdBy.setUsername("username");
//        project.setCreatedBy(createdBy);
//        project.setModifiedOn(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        final User modifiedBy = new User();
//        modifiedBy.setId(0L);
//        modifiedBy.setName("name");
//        modifiedBy.setEmail("email");
//        modifiedBy.setUsername("username");
//        project.setModifiedBy(modifiedBy);
//        final List<Project> projects = List.of(project);
//        when(mockProjectRepository.findAll()).thenReturn(projects);
//
//        when(mockIssueService.getIssueFromProjectByAssignedToAndCreatedBy(0L))
//                .thenThrow(ProjectNotFoundException.class);
//
//        // Run the test
//        assertThatThrownBy(() -> projectServiceImplUnderTest.getAllProjectsByCreatedUser())
//                .isInstanceOf(ProjectNotFoundException.class);
//    }

//    @Test
//    void testGetProjectById() throws Exception {
//        // Setup
//        final ProjectDTO expectedResult = new ProjectDTO();
//        expectedResult.setId(0L);
//        expectedResult.setName("name");
//        expectedResult.setProjectDescription("projectDescription");
//        expectedResult.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        expectedResult.setTargetEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        expectedResult.setActualEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        expectedResult.setCreatedOn(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        final UserDTO createdBy = new UserDTO();
//        createdBy.setId(0L);
//        createdBy.setName("name");
//        createdBy.setUsername("username");
//        createdBy.setEmail("email");
//        createdBy.setAssignedProjects(Set.of(new ProjectDTO()));
//        final RoleDTO roleDTO = new RoleDTO();
//        roleDTO.setId(0L);
//        createdBy.setRoles(Set.of(roleDTO));
//        expectedResult.setCreatedBy(createdBy);
//
//        // Configure ProjectRepository.findById(...).
//        final Project project1 = new Project();
//        project1.setId(0L);
//        project1.setName("name");
//        project1.setProjectDescription("projectDescription");
//        project1.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        project1.setTargetEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        project1.setActualEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        project1.setCreatedOn(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        final User createdBy1 = new User();
//        createdBy1.setId(0L);
//        createdBy1.setName("name");
//        createdBy1.setEmail("email");
//        createdBy1.setUsername("username");
//        project1.setCreatedBy(createdBy1);
//        project1.setModifiedOn(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        final User modifiedBy = new User();
//        modifiedBy.setId(0L);
//        modifiedBy.setName("name");
//        modifiedBy.setEmail("email");
//        modifiedBy.setUsername("username");
//        project1.setModifiedBy(modifiedBy);
//        final Optional<Project> project = Optional.of(project1);
//        when(mockProjectRepository.findById(0L)).thenReturn(project);
//
//        // Run the test
//        final ProjectDTO result = projectServiceImplUnderTest.getProjectById(0L);
//
//        // Verify the results
//        assertThat(result).isEqualTo(expectedResult);
//    }



//    @Test
//    void testGetProjectById_ThrowsException() {
//        // Setup
//        // Configure ProjectRepository.findById(...).
//        final Project project1 = new Project();
//        project1.setId(0L);
//        project1.setName("name");
//        project1.setProjectDescription("projectDescription");
//        project1.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        project1.setTargetEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        project1.setActualEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        project1.setCreatedOn(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        final User createdBy = new User();
//        createdBy.setId(0L);
//        createdBy.setName("name");
//        createdBy.setEmail("email");
//        createdBy.setUsername("username");
//        project1.setCreatedBy(createdBy);
//        project1.setModifiedOn(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        final User modifiedBy = new User();
//        modifiedBy.setId(0L);
//        modifiedBy.setName("name");
//        modifiedBy.setEmail("email");
//        modifiedBy.setUsername("username");
//        project1.setModifiedBy(modifiedBy);
//        final Optional<Project> project = Optional.of(project1);
//        when(mockProjectRepository.findById(0L)).thenReturn(project);
//
//        // Run the test
//        assertThatThrownBy(() -> projectServiceImplUnderTest.getProjectById(0L)).isInstanceOf(Exception.class);
//    }

//    @Test
//    void testAddProject() {
//        // Setup
//        final ProjectDTO projectDTO = new ProjectDTO();
//        projectDTO.setId(0L);
//        projectDTO.setName("name");
//        projectDTO.setProjectDescription("projectDescription");
//        projectDTO.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        projectDTO.setTargetEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        projectDTO.setActualEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        projectDTO.setCreatedOn(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        final UserDTO createdBy = new UserDTO();
//        createdBy.setId(0L);
//        createdBy.setName("name");
//        createdBy.setUsername("username");
//        createdBy.setEmail("email");
//        createdBy.setAssignedProjects(Set.of(new ProjectDTO()));
//        final RoleDTO roleDTO = new RoleDTO();
//        roleDTO.setId(0L);
//        createdBy.setRoles(Set.of(roleDTO));
//        projectDTO.setCreatedBy(createdBy);
//
//        final ProjectDTO expectedResult = new ProjectDTO();
//        expectedResult.setId(0L);
//        expectedResult.setName("name");
//        expectedResult.setProjectDescription("projectDescription");
//        expectedResult.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        expectedResult.setTargetEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        expectedResult.setActualEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        expectedResult.setCreatedOn(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        final UserDTO createdBy1 = new UserDTO();
//        createdBy1.setId(0L);
//        createdBy1.setName("name");
//        createdBy1.setUsername("username");
//        createdBy1.setEmail("email");
//        createdBy1.setAssignedProjects(Set.of(new ProjectDTO()));
//        final RoleDTO roleDTO1 = new RoleDTO();
//        roleDTO1.setId(0L);
//        createdBy1.setRoles(Set.of(roleDTO1));
//        expectedResult.setCreatedBy(createdBy1);
//
//        // Configure AuthenticationService.getUserInfo(...).
//        final UserDTO userDTO = new UserDTO();
//        userDTO.setId(0L);
//        userDTO.setName("name");
//        userDTO.setUsername("username");
//        userDTO.setEmail("email");
//        final ProjectDTO projectDTO1 = new ProjectDTO();
//        projectDTO1.setId(0L);
//        projectDTO1.setName("name");
//        projectDTO1.setProjectDescription("projectDescription");
//        projectDTO1.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        projectDTO1.setTargetEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        projectDTO1.setActualEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        projectDTO1.setCreatedOn(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        userDTO.setAssignedProjects(Set.of(projectDTO1));
//        final RoleDTO roleDTO2 = new RoleDTO();
//        roleDTO2.setId(0L);
//        roleDTO2.setName("name");
//        userDTO.setRoles(Set.of(roleDTO2));
//        when(mockAuthenticationService.getUserInfo()).thenReturn(userDTO);
//
//        // Configure ProjectRepository.save(...).
//        final Project project = new Project();
//        project.setId(0L);
//        project.setName("name");
//        project.setProjectDescription("projectDescription");
//        project.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        project.setTargetEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        project.setActualEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        project.setCreatedOn(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        final User createdBy2 = new User();
//        createdBy2.setId(0L);
//        createdBy2.setName("name");
//        createdBy2.setEmail("email");
//        createdBy2.setUsername("username");
//        project.setCreatedBy(createdBy2);
//        project.setModifiedOn(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        final User modifiedBy = new User();
//        modifiedBy.setId(0L);
//        modifiedBy.setName("name");
//        modifiedBy.setEmail("email");
//        modifiedBy.setUsername("username");
//        project.setModifiedBy(modifiedBy);
//        when(mockProjectRepository.save(new Project())).thenReturn(project);
//
//        // Run the test
//        final ProjectDTO result = projectServiceImplUnderTest.addProject(projectDTO);
//
//        // Verify the results
//        assertThat(result).isEqualTo(expectedResult);
//    }



//    @Test
//    void testUpdateProject() throws Exception {
//        // Setup
//        final ProjectDTO projectDTO = new ProjectDTO();
//        projectDTO.setId(0L);
//        projectDTO.setName("name");
//        projectDTO.setProjectDescription("projectDescription");
//        projectDTO.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        projectDTO.setTargetEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        projectDTO.setActualEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        projectDTO.setCreatedOn(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        final UserDTO createdBy = new UserDTO();
//        createdBy.setId(0L);
//        createdBy.setName("name");
//        createdBy.setUsername("username");
//        createdBy.setEmail("email");
//        createdBy.setAssignedProjects(Set.of(new ProjectDTO()));
//        final RoleDTO roleDTO = new RoleDTO();
//        roleDTO.setId(0L);
//        createdBy.setRoles(Set.of(roleDTO));
//        projectDTO.setCreatedBy(createdBy);
//
//        final ProjectDTO expectedResult = new ProjectDTO();
//        expectedResult.setId(0L);
//        expectedResult.setName("name");
//        expectedResult.setProjectDescription("projectDescription");
//        expectedResult.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        expectedResult.setTargetEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        expectedResult.setActualEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        expectedResult.setCreatedOn(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        final UserDTO createdBy1 = new UserDTO();
//        createdBy1.setId(0L);
//        createdBy1.setName("name");
//        createdBy1.setUsername("username");
//        createdBy1.setEmail("email");
//        createdBy1.setAssignedProjects(Set.of(new ProjectDTO()));
//        final RoleDTO roleDTO1 = new RoleDTO();
//        roleDTO1.setId(0L);
//        createdBy1.setRoles(Set.of(roleDTO1));
//        expectedResult.setCreatedBy(createdBy1);
//
//        // Configure ProjectRepository.findById(...).
//        final Project project1 = new Project();
//        project1.setId(0L);
//        project1.setName("name");
//        project1.setProjectDescription("projectDescription");
//        project1.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        project1.setTargetEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        project1.setActualEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        project1.setCreatedOn(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        final User createdBy2 = new User();
//        createdBy2.setId(0L);
//        createdBy2.setName("name");
//        createdBy2.setEmail("email");
//        createdBy2.setUsername("username");
//        project1.setCreatedBy(createdBy2);
//        project1.setModifiedOn(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        final User modifiedBy = new User();
//        modifiedBy.setId(0L);
//        modifiedBy.setName("name");
//        modifiedBy.setEmail("email");
//        modifiedBy.setUsername("username");
//        project1.setModifiedBy(modifiedBy);
//        final Optional<Project> project = Optional.of(project1);
//        when(mockProjectRepository.findById(0L)).thenReturn(project);
//
//        // Configure AuthenticationService.getUserInfo(...).
//        final UserDTO userDTO = new UserDTO();
//        userDTO.setId(0L);
//        userDTO.setName("name");
//        userDTO.setUsername("username");
//        userDTO.setEmail("email");
//        final ProjectDTO projectDTO1 = new ProjectDTO();
//        projectDTO1.setId(0L);
//        projectDTO1.setName("name");
//        projectDTO1.setProjectDescription("projectDescription");
//        projectDTO1.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        projectDTO1.setTargetEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        projectDTO1.setActualEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        projectDTO1.setCreatedOn(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        userDTO.setAssignedProjects(Set.of(projectDTO1));
//        final RoleDTO roleDTO2 = new RoleDTO();
//        roleDTO2.setId(0L);
//        roleDTO2.setName("name");
//        userDTO.setRoles(Set.of(roleDTO2));
//        when(mockAuthenticationService.getUserInfo()).thenReturn(userDTO);
//
//        // Configure ProjectRepository.save(...).
//        final Project project2 = new Project();
//        project2.setId(0L);
//        project2.setName("name");
//        project2.setProjectDescription("projectDescription");
//        project2.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        project2.setTargetEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        project2.setActualEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        project2.setCreatedOn(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        final User createdBy3 = new User();
//        createdBy3.setId(0L);
//        createdBy3.setName("name");
//        createdBy3.setEmail("email");
//        createdBy3.setUsername("username");
//        project2.setCreatedBy(createdBy3);
//        project2.setModifiedOn(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        final User modifiedBy1 = new User();
//        modifiedBy1.setId(0L);
//        modifiedBy1.setName("name");
//        modifiedBy1.setEmail("email");
//        modifiedBy1.setUsername("username");
//        project2.setModifiedBy(modifiedBy1);
//        when(mockProjectRepository.save(new Project())).thenReturn(project2);
//
//        // Run the test
//        final ProjectDTO result = projectServiceImplUnderTest.updateProject(0L, projectDTO);
//
//        // Verify the results
//        assertThat(result).isEqualTo(expectedResult);
//    }


//    @Test
//    void testUpdateProject_ThrowsProjectNotFoundException() {
//        // Setup
//        final ProjectDTO projectDTO = new ProjectDTO();
//        projectDTO.setId(0L);
//        projectDTO.setName("name");
//        projectDTO.setProjectDescription("projectDescription");
//        projectDTO.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        projectDTO.setTargetEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        projectDTO.setActualEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        projectDTO.setCreatedOn(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        final UserDTO createdBy = new UserDTO();
//        createdBy.setId(0L);
//        createdBy.setName("name");
//        createdBy.setUsername("username");
//        createdBy.setEmail("email");
//        createdBy.setAssignedProjects(Set.of(new ProjectDTO()));
//        final RoleDTO roleDTO = new RoleDTO();
//        roleDTO.setId(0L);
//        createdBy.setRoles(Set.of(roleDTO));
//        projectDTO.setCreatedBy(createdBy);
//
//        // Configure ProjectRepository.findById(...).
//        final Project project1 = new Project();
//        project1.setId(0L);
//        project1.setName("name");
//        project1.setProjectDescription("projectDescription");
//        project1.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        project1.setTargetEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        project1.setActualEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        project1.setCreatedOn(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        final User createdBy1 = new User();
//        createdBy1.setId(0L);
//        createdBy1.setName("name");
//        createdBy1.setEmail("email");
//        createdBy1.setUsername("username");
//        project1.setCreatedBy(createdBy1);
//        project1.setModifiedOn(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        final User modifiedBy = new User();
//        modifiedBy.setId(0L);
//        modifiedBy.setName("name");
//        modifiedBy.setEmail("email");
//        modifiedBy.setUsername("username");
//        project1.setModifiedBy(modifiedBy);
//        final Optional<Project> project = Optional.of(project1);
//        when(mockProjectRepository.findById(0L)).thenReturn(project);
//
//        // Configure AuthenticationService.getUserInfo(...).
//        final UserDTO userDTO = new UserDTO();
//        userDTO.setId(0L);
//        userDTO.setName("name");
//        userDTO.setUsername("username");
//        userDTO.setEmail("email");
//        final ProjectDTO projectDTO1 = new ProjectDTO();
//        projectDTO1.setId(0L);
//        projectDTO1.setName("name");
//        projectDTO1.setProjectDescription("projectDescription");
//        projectDTO1.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        projectDTO1.setTargetEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        projectDTO1.setActualEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        projectDTO1.setCreatedOn(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        userDTO.setAssignedProjects(Set.of(projectDTO1));
//        final RoleDTO roleDTO1 = new RoleDTO();
//        roleDTO1.setId(0L);
//        roleDTO1.setName("name");
//        userDTO.setRoles(Set.of(roleDTO1));
//        when(mockAuthenticationService.getUserInfo()).thenReturn(userDTO);
//
//        // Configure ProjectRepository.save(...).
//        final Project project2 = new Project();
//        project2.setId(0L);
//        project2.setName("name");
//        project2.setProjectDescription("projectDescription");
//        project2.setStartDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        project2.setTargetEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        project2.setActualEndDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        project2.setCreatedOn(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        final User createdBy2 = new User();
//        createdBy2.setId(0L);
//        createdBy2.setName("name");
//        createdBy2.setEmail("email");
//        createdBy2.setUsername("username");
//        project2.setCreatedBy(createdBy2);
//        project2.setModifiedOn(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
//        final User modifiedBy1 = new User();
//        modifiedBy1.setId(0L);
//        modifiedBy1.setName("name");
//        modifiedBy1.setEmail("email");
//        modifiedBy1.setUsername("username");
//        project2.setModifiedBy(modifiedBy1);
//        when(mockProjectRepository.save(new Project())).thenReturn(project2);
//
//        // Run the test
//        assertThatThrownBy(() -> projectServiceImplUnderTest.updateProject(0L, projectDTO))
//                .isInstanceOf(ProjectNotFoundException.class);
//    }
}
