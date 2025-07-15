package com.docker.jenkins.integration.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/DockerJenkins")
public class DockerJenkinsIntegrationController {
	
	@GetMapping(value = "/welcome")
	public String getStringValue() {
		return "Welcome to Docker Jenkins Integration Application";
	}

	@GetMapping(value = "/name")
	public String getName() {
		return "My name is Prakash";
	}

}
