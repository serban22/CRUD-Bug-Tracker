package com.sda.finalproject.sda.Bugtracker.Entities.DTO;

import com.sda.finalproject.sda.Bugtracker.Entities.ProjectEntity;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDTO {

    public Integer projectId;
    public String projectName;
    public String projectShortName;
    public String projectDescription;
    public String projectStatusName;

    public ProjectDTO(ProjectEntity projectEntity) {
        this.projectId = projectEntity.getProjectId();
        this.projectName = projectEntity.getProjectName();
        this.projectShortName = projectEntity.getProjectShortName();
        this.projectDescription = projectEntity.getProjectDescription();

        if (projectEntity.getProjectStatusName() != null) {
            this.projectStatusName = projectEntity.getProjectStatusName().getStatusName();
        } else {
            this.projectStatusName = "Undefined status.";
        }
    }
}
