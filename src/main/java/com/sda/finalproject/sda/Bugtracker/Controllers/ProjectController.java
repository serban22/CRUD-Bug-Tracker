package com.sda.finalproject.sda.Bugtracker.Controllers;

import com.sda.finalproject.sda.Bugtracker.Entities.DTO.ProjectDTO;
import com.sda.finalproject.sda.Bugtracker.Entities.ProjectEntity;
import com.sda.finalproject.sda.Bugtracker.Services.Converters.ProjectConverter;
import com.sda.finalproject.sda.Bugtracker.Services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectConverter projectConverter;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping(value = "/project/{projectId}", produces = "application/json")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable Integer projectId) {
        ProjectEntity projectById = projectService.findByProjectId(projectId);
        if (projectById != null) {
            ProjectEntity projectEntity = projectById;
            ProjectDTO projectDTO = new ProjectDTO(projectEntity);
            return new ResponseEntity<ProjectDTO>(projectDTO, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The project with the id " + projectId + " doesn't exist.");
        }
    }

    @GetMapping(value = "/projects", produces = "application/json")
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        List<ProjectDTO> projects = projectService.getAllProjects();
        return new ResponseEntity<List<ProjectDTO>>(projects, HttpStatus.OK);
    }

    @PostMapping(value = "create-new-project", consumes = "application/json")
    public ResponseEntity<ProjectEntity> createProject(@RequestBody ProjectDTO newProject) {
        ProjectEntity projectEntity = projectService.saveProject(newProject);
        return new ResponseEntity<ProjectEntity>(projectEntity, HttpStatus.OK);
    }

    @PostMapping(value = "/finalProjectBugTracker/project/saveProjectWithConversion", consumes = "application/json")
    public ResponseEntity<ProjectEntity> saveProjectWithConversion(@RequestBody ProjectDTO projectDTO) {
        ProjectEntity projectEntity = projectService.saveProjectWithConversion(projectDTO);
        return new ResponseEntity<ProjectEntity>(projectEntity, HttpStatus.OK);
    }

    @PutMapping(value = "/update-project/{projectId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ProjectEntity> updateProjectById(@PathVariable(name = "projectId") Integer projectId, @RequestBody ProjectDTO projectDTO) {
        ProjectEntity projectEntity = projectService.findByProjectId(projectId);
        if (projectEntity != null) {
            ProjectEntity anotherProjectEntity = projectService.updateProjectById(projectId, projectDTO);
            return new ResponseEntity<ProjectEntity>(anotherProjectEntity, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The project with the id " + projectId + " doesn't exist.");
        }
    }

    @DeleteMapping("/project/{projectId}")
    public void deleteProjectById(@PathVariable Integer projectId) {
        if (projectService.isProjectIdPresent(projectId)) {
            projectService.deleteProjectById(projectId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The project with the id " + projectId + " doesn't exist.");
        }
    }
}
