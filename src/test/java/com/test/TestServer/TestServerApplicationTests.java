package com.test.TestServer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TestServerApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void shouldReturn401() throws Exception {
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isUnauthorized());
	}

	@Test
	void shouldReturnDefaultMessage() throws Exception {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("Authorization","Basic YWRtaW46YWRtaW4tcGFzc3dvcmQ=");
		this.mockMvc.perform(get("/").headers(httpHeaders)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello, World")));
	}

}
