package com.sda.finalproject.sda.Bugtracker.Controllers.WebControllers;

import com.sda.finalproject.sda.Bugtracker.Repositories.ProjectRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProjectWebController {

    private final ProjectRepository projectRepository;


    public ProjectWebController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping("/projects")
    public ModelAndView projects() {
        ModelAndView modelAndView = new ModelAndView("projects");
        return modelAndView;
    }
}
