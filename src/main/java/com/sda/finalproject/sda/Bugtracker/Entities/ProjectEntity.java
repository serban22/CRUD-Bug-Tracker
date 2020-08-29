package com.sda.finalproject.sda.Bugtracker.Entities;

import javax.persistence.*;

import com.sda.finalproject.sda.Bugtracker.Entities.DTO.ProjectDTO;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "project")
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    Integer projectId;

    @Column(name = "project_name")
    String projectName;

    @Column(name = "project_shortname")
    String projectShortName;

    @Column(name = "project_description")
    String projectDescription;

    @ManyToOne
    @JoinColumn(name = "status_id")
    StatusEntity projectStatusName;

    public ProjectEntity(ProjectDTO projectDTO, StatusEntity statusEntity) {
    }
}
