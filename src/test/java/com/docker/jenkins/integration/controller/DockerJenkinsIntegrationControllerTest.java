package com.docker.jenkins.integration.controller;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
//@RunWith(SpringRunner.class)
class DockerJenkinsIntegrationControllerTest {
	
	
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext context;
	
	@InjectMocks
	private DockerJenkinsIntegrationController dockerJenkinsIntegrationController;
	
	@Before
	public void setUp() {
		this.mockMvc =  MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void getStringValueTest() throws Exception{
		
		mockMvc.perform(get("/welcome"))
							.andExpect(status().isOk())
							.andExpect(content().string("Welcome to Docker Jenkins Integration Application"));
		
	}

}
