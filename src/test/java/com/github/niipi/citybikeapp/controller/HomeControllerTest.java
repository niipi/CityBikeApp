package com.github.niipi.citybikeapp.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
class HomeControllerTest extends ControllerTest{

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new HomeController()).build();
    }

    @Test
    void index() throws Exception {
        this.mockMvc
                .perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("index.html"));
    }
}