package com.temesgenbesha.projectmanagementsystem.util;

import com.temesgenbesha.projectmanagementsystem.entity.Issue;
import com.temesgenbesha.projectmanagementsystem.entity.Priority;
import com.temesgenbesha.projectmanagementsystem.entity.Project;
import com.temesgenbesha.projectmanagementsystem.entity.Role;
import com.temesgenbesha.projectmanagementsystem.entity.Status;
import com.temesgenbesha.projectmanagementsystem.entity.User;
import com.temesgenbesha.projectmanagementsystem.repository.IssueRepository;
import com.temesgenbesha.projectmanagementsystem.repository.ProjectRepository;
import com.temesgenbesha.projectmanagementsystem.repository.RoleRepository;
import com.temesgenbesha.projectmanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataGeneratorUtil implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final IssueRepository issueRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Role userRole = new Role();
        userRole.setName("ROLE_USER");
        roleRepository.save(userRole);

        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        roleRepository.save(adminRole);

        User admin = new User();
        admin.setName("Temu Beesha");
        admin.setEmail("temub@email.com");
        admin.setUsername("temub");
        admin.setPassword(passwordEncoder.encode("1298jdnsjnn"));
        admin.setCreatedOn(ZonedDateTime.now().minusDays(2L));
        admin.setAssignedProjects(Collections.emptySet());
        admin.setRoles(Set.of(userRole, adminRole));
        admin = userRepository.save(admin); // this will save all the admin listed info's including ID and override it to the user admin
//so if we want to use the user admin in the other functionalities the user admin will be used with their ID



        User user1 = new User();
        user1.setName("Micky Maraki");
        user1.setEmail("Msquere@email.com");
        user1.setUsername("mm");
        user1.setPassword(passwordEncoder.encode("63274ghedbc"));
        user1.setCreatedOn(ZonedDateTime.now().minusDays(1L));
        user1.setModifiedBy(admin);
        user1.setModifiedOn(ZonedDateTime.now());
        user1.setAssignedProjects(Collections.emptySet());
        user1.setRoles(Set.of(userRole));
        user1 = userRepository.save(user1);

        User user2 = new User();
        user2.setName("Jon Doe");
        user2.setEmail("jondoe@email.com");
        user2.setUsername("jondoe");
        user2.setPassword(passwordEncoder.encode("asdadfsdf45"));
        user2.setCreatedOn(ZonedDateTime.now());
        user2.setAssignedProjects(Collections.emptySet());
        user2.setRoles(Set.of(userRole));
        user2 = userRepository.save(user2);

        Project project1 = new Project();
        project1.setName("First Software project");
        project1.setProjectDescription("Lorem ipsum dolor sit amet adipiscing");
        project1.setCreatedOn(LocalDateTime.now());
        project1.setStartDate(LocalDateTime.now().plusDays(2L));
        project1.setTargetEndDate(LocalDateTime.now().plusWeeks(6L));
        project1.setCreatedBy(admin);
        project1 = projectRepository.save(project1);

        Project project2 = new Project();
        project2.setName("IBM project");
        project2.setProjectDescription("Lorem ipsum dolor sit amet adipiscing");
        project2.setCreatedOn(LocalDateTime.now());
        project2.setStartDate(LocalDateTime.now().plusDays(3L));
        project2.setTargetEndDate(LocalDateTime.now().plusWeeks(8L));
        project2.setCreatedBy(user1);
        project2 = projectRepository.save(project2);

        Issue issue1 = new Issue();
        issue1.setSummary("Example Summary");
        issue1.setDescription("I have no idea where to start");
        issue1.setCreatedBy(user1);
        issue1.setCreatedOn(LocalDateTime.now().minusDays(2L));
        issue1.setAssignedTo(user2);
        issue1.setAssignedOn(LocalDateTime.now());
        issue1.setStatus(Status.OPEN);
        issue1.setPriority(Priority.LOW);
        issue1.setTargetResolutionDate(LocalDateTime.now().plusWeeks(2L));
        issue1.setProject(project1);
        issue1 = issueRepository.save(issue1);
    }
}
