package com.example.devopsapp.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.devopsapp.service.GreetingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(HelloController.class)
@Import(GreetingService.class)
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void homeReturnsMessage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void greetUsesNameParameter() throws Exception {
        mockMvc.perform(get("/api/greet").param("name", "Arun"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.greeting").value("Hello, Arun!"));
    }

    @Test
    void greetDefaultsToWorld() throws Exception {
        mockMvc.perform(get("/api/greet"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.greeting").value("Hello, World!"));
    }

    @Test
    void infoReturnsAppDetails() throws Exception {
        mockMvc.perform(get("/api/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.app").value("devops-app"));
    }
}
