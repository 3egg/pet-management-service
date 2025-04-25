package com.test.TestServer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class TestServerApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testIsUnauthorized() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/"))
				.andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}

	@Test
	void testIsOk() throws Exception {
		HttpHeaders header =  new HttpHeaders();
		header.add("Authorization", "Basic YWRtaW46YWRtaW4tcGFzc3dvcmQ=");
		this.mockMvc.perform(MockMvcRequestBuilders.get("/").headers(header))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("Hello, World!"));
	}

}
