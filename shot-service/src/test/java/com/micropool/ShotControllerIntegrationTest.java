package com.micropool;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ShotControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void postShotReturnsOk() throws Exception {
        mockMvc.perform(post("/shots")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"angle\": 45, \"power\": 50, \"spin\": 0}"))
                .andExpect(status().isOk());
    }
}
