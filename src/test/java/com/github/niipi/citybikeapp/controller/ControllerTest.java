package com.github.niipi.citybikeapp.controller;

import com.github.niipi.citybikeapp.CitybikeApplication;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


/** Superclass for controller test classes. Provides a common MockMvc and WebApplicationContext for all Controller tests. **/
@SpringBootTest
@ContextConfiguration(classes= CitybikeApplication.class)
@AutoConfigureMockMvc
public class ControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
}
