package com.sda.finalproject.sda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class FinalProjectBugTrackerApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(FinalProjectBugTrackerApplication.class, args);
	}

}
