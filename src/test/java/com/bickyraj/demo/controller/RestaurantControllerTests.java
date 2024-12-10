package com.bickyraj.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class RestaurantControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetRestaurantByAddress() throws Exception {
        mockMvc.perform(get("/restaurant/paris"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address").value("paris"));
    }

    @Test
    public void testNotFoundRestaurantByAddress() throws Exception {
        mockMvc.perform(get("/restaurant/test"))
                .andExpect(status().isNotFound());
    }
}
