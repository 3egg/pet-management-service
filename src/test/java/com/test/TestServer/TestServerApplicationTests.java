package com.test.TestServer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
class TestServerApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() throws Exception {
        mockMvc.perform(get("/").header("Authorization", "Basic YWRtaW46YWRtaW4tcGFzc3dvcmQ="))
                .andExpect(status().isOk()).andExpect(content().string("Hello, World!"));
        ;
    }

}
