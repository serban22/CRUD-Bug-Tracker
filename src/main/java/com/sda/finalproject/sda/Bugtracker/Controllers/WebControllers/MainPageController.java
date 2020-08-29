package com.sda.finalproject.sda.Bugtracker.Controllers.WebControllers;

import com.sda.finalproject.sda.Bugtracker.Services.ProjectService;
import com.sda.finalproject.sda.Bugtracker.Services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainPageController {

    @Autowired
    StatusService statusService;

    @Autowired
    ProjectService projectService;

    @GetMapping("/")
    public ModelAndView mainPageController() {
        ModelAndView modelAndView = new ModelAndView("mainPage");
        return modelAndView;
    }
}
