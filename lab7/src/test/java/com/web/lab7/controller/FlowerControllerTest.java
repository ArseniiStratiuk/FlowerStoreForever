package com.web.lab7.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.web.lab7.PostgresContainerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class FlowerControllerTest extends PostgresContainerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllReturnsSeedData() throws Exception {
        mockMvc.perform(get("/api/v1/flower"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    void postCreatesFlower() throws Exception {
        String payload = """
                {
                  \"type\": \"ROSE\",
                  \"color\": \"RED\",
                  \"sepalLength\": 15,
                  \"price\": 25
                }
                """;

        mockMvc.perform(post("/api/v1/flower")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.type").value("ROSE"));
    }
}
