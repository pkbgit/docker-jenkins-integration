package com.docker.jenkins.integration.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(value = "/DockerJenkins")
public class DockerJenkinsIntegrationController {
	
	@GetMapping(value = "/welcome")
	public String getStringValue() {
		return "Hi !!! Welcome to Docker Jenkins Integration Application.";
	}

	@GetMapping(value = "/name")
	public String getName() {
		return "My name is Prakash";
	}

	@GetMapping(value = "/student/{name}")
	public String getStudentString(@PathVariable String name)
	{
	    if("Prakash".equals(name)){
			return "Male-40-Cuttack";
		}else if("Arpita".equals(name)){
			return "Female-10-Bhubaneswar";
		}
	    return "Wrong Input";
	}

}
