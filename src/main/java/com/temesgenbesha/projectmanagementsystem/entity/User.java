package com.temesgenbesha.projectmanagementsystem.entity;

import com.temesgenbesha.projectmanagementsystem.dto.UserDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String email;
    private String username;
    private String password;
    private ZonedDateTime createdOn;
    private ZonedDateTime modifiedOn;

    @OneToOne
    private User modifiedBy;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Project> assignedProjects;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public UserDTO toDTO() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setName(name);
        userDTO.setEmail(email);
        userDTO.setUsername(username);
        if (assignedProjects != null) {
            userDTO.setAssignedProjects(assignedProjects.stream().map(Project::toDTO).collect(Collectors.toSet()));
        }
        if (roles != null) {
            userDTO.setRoles(roles.stream().map(Role::toDTO).collect(Collectors.toSet()));
        }

        return userDTO;
    }
}
