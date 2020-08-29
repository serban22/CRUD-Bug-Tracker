package com.sda.finalproject.sda.Bugtracker.Services.Converters;

import com.sda.finalproject.sda.Bugtracker.Entities.DTO.ProjectDTO;
import com.sda.finalproject.sda.Bugtracker.Entities.ProjectEntity;
import org.springframework.stereotype.Service;

@Service
public class ProjectConverter implements EntityConverter<ProjectEntity, ProjectDTO> {

    @Override
    public ProjectDTO convertToDTO(ProjectEntity projectEntity) {
        ProjectDTO dto = new ProjectDTO();
        dto.projectId = projectEntity.getProjectId();
        dto.projectName = projectEntity.getProjectName();
        dto.projectShortName = projectEntity.getProjectShortName();
        dto.projectDescription = projectEntity.getProjectDescription();

        if (projectEntity.getProjectStatusName() != null) {
            dto.projectStatusName = projectEntity.getProjectStatusName().getStatusName();
        } else {
            dto.projectStatusName = "Undefined status.";
        }
        return dto;
    }

    @Override
    public ProjectEntity convertToEntity(ProjectDTO projectDTO) {
        ProjectEntity projectEntity = new ProjectEntity();

        projectEntity.setProjectName(projectDTO.projectName);
        projectEntity.setProjectShortName(projectDTO.projectShortName);
        projectEntity.setProjectDescription(projectDTO.projectDescription);

        return projectEntity;
    }
}
