package com.web.atrio;

import org.apache.commons.configuration.ConfigurationException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) throws ConfigurationException {
		SpringApplication.run(ProjectApplication.class, args);
	}
}
