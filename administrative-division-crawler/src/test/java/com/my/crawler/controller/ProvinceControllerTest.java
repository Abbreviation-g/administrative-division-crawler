package com.my.crawler.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.my.crawler.ServiceMainApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ServiceMainApplication.class })
public class ProvinceControllerTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testCombox() {

		try {
			RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/province" + "/combox");
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			MockHttpServletResponse response = result.getResponse();
			byte[] buff = response.getContentAsByteArray();
			String jsonString = new String(buff);
			
			System.out.println(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
