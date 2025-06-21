package com.test.TestServer;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
class TestServerApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testIsUnauthorized() throws Exception {
		this.mockMvc.perform(get("/"))
				.andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}

	@Test
	void testIsOk() throws Exception {
		HttpHeaders header =  new HttpHeaders();
		header.add("Authorization", "Basic YWRtaW46YWRtaW4tcGFzc3dvcmQ=");
		this.mockMvc.perform(get("/").headers(header))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("Hello, World!"));
	}

}
