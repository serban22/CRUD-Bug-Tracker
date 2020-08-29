package com.sda.finalproject.sda.Bugtracker.Services;

import com.sda.finalproject.sda.Bugtracker.Entities.DTO.ProjectDTO;
import com.sda.finalproject.sda.Bugtracker.Entities.ProjectEntity;
import com.sda.finalproject.sda.Bugtracker.Entities.StatusEntity;
import com.sda.finalproject.sda.Bugtracker.Repositories.ProjectRepository;
import com.sda.finalproject.sda.Bugtracker.Repositories.StatusRepository;
import com.sda.finalproject.sda.Bugtracker.Services.Converters.ProjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    ProjectConverter projectConverter;

    @Transactional
    public Boolean isProjectIdPresent(Integer id) {
        return projectRepository.findById(id).isPresent();
    }

    @Transactional
    public ProjectEntity findByProjectId(Integer id) {
    Optional<ProjectEntity> byId = projectRepository.findProjectEntityByProjectId(id);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            throw new RuntimeException("The project with the id " + id + " could not be found.");
        }
    }

    @Transactional
    public List<ProjectEntity> findByProjectName(String name) {
        return projectRepository.findByProjectName(name);
    }

    @Transactional
    public ProjectEntity saveProject(ProjectDTO projectDTO) {
        Optional<StatusEntity> byStatusName = statusRepository.findByStatusName(projectDTO.projectStatusName);

        StatusEntity statusEntity = null;
        if (!byStatusName.isPresent()) {
            StatusEntity newStatus = new StatusEntity(projectDTO.projectStatusName);
            statusEntity = statusRepository.save(newStatus);
        } else {
            statusEntity = byStatusName.get();
        }

        ProjectEntity projectEntityToBeSaved = new ProjectEntity(projectDTO, statusEntity);
        ProjectEntity savedProject = projectRepository.save(projectEntityToBeSaved);
        return savedProject;
    }

    @Transactional
    public List<ProjectDTO> getAllProjects() {

        List<ProjectEntity> allProjects = projectRepository.findAll();
        List<ProjectDTO> projectDTOS = new ArrayList<>();
        for (ProjectEntity projectEntity : allProjects) {
            ProjectDTO tempProject = new ProjectDTO();
            tempProject.projectId = projectEntity.getProjectId();
            tempProject.projectName = projectEntity.getProjectName();
            tempProject.projectShortName = projectEntity.getProjectShortName();
            tempProject.projectDescription = projectEntity.getProjectDescription();
            tempProject.projectStatusName = projectEntity.getProjectStatusName().getStatusName();

            projectDTOS.add(tempProject);
        }
        return projectDTOS;
    }

    @Transactional
    public ProjectEntity updateProjectById(Integer id, ProjectDTO projectToBeUpdated) {
        ProjectEntity projectEntity;
        StatusEntity statusEntity;

        Optional<StatusEntity> statusEntityOptional = statusRepository.findByStatusName(projectToBeUpdated.projectStatusName);
        Optional<ProjectEntity> projectEntityOptional = projectRepository.findById(id);

        if (!projectEntityOptional.isPresent()) {
            throw new RuntimeException("Could not find the project with the id: " + id);
        } else {
            projectEntity = projectEntityOptional.get();
            statusEntity = statusEntityOptional.get();

            statusEntity.setStatusName(projectToBeUpdated.projectStatusName);
            projectEntity.setProjectName(projectToBeUpdated.projectName);
            projectEntity.setProjectShortName(projectToBeUpdated.projectShortName);
            projectEntity.setProjectDescription(projectToBeUpdated.projectDescription);
            projectEntity.setProjectStatusName(statusEntity);
        }
        return projectRepository.save(projectEntity);
    }

    @Transactional
    public void deleteProjectById(Integer id) {
        projectRepository.deleteById(id);
    }

    @Transactional
    public ProjectEntity saveProjectWithConversion(ProjectDTO projectDTO) {
        ProjectEntity projectEntityToSave = projectConverter.convertToEntity(projectDTO);
        return projectEntityToSave;
    }
}

