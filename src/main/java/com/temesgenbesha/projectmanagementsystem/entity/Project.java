package com.temesgenbesha.projectmanagementsystem.entity;

import com.sun.istack.NotNull;
import com.temesgenbesha.projectmanagementsystem.dto.ProjectDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    private String projectDescription;
//    @DateTimeFormat(pattern = "yyyy-MM-ddhh:mm")
    private LocalDateTime startDate;
   // @DateTimeFormat(pattern = "yyyy-MM-ddhh:mm")
    private LocalDateTime targetEndDate;

    private LocalDateTime actualEndDate;

    private LocalDateTime createdOn;

    @OneToOne
    private User createdBy;
    private LocalDateTime modifiedOn;

    @OneToOne
    private User modifiedBy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Project project = (Project) o;
        return id != null && Objects.equals(id, project.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public ProjectDTO toDTO() {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(id);
        projectDTO.setName(name);
        projectDTO.setProjectDescription(projectDescription);
        projectDTO.setStartDate(startDate);
        projectDTO.setTargetEndDate(targetEndDate);
        projectDTO.setActualEndDate(actualEndDate);
        projectDTO.setCreatedOn(createdOn);
        if (createdBy != null) {
            projectDTO.setCreatedBy(createdBy.toDTO());
        }

        return projectDTO;
    }
}
