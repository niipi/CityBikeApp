package com.github.niipi.citybikeapp.controller;

import com.github.niipi.citybikeapp.CitybikeApplication;
import com.github.niipi.citybikeapp.model.Journey;
import com.github.niipi.citybikeapp.repository.JourneyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@ContextConfiguration(classes= CitybikeApplication.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class JourneyControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private JourneyRepository repository;

    private Journey testJourney1 = new Journey(LocalDateTime.of(2021, 5, 31, 23, 52, 3), LocalDateTime.of(2021, 6 , 1, 01,06,23), Long.parseLong("501"), "Hanasaari", Long.parseLong("502"), "Kulosaari", 6785, 345);

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext) {
        repository.save(testJourney1);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new JourneyController(repository)).build();
    }

    @Test
    void shouldReturnAllJourneysAsJSON() throws Exception {
        ArrayList<Journey> expectedJourneys = new ArrayList<Journey>();
        expectedJourneys.add(testJourney1);
        int expectedTotalPages = 1;
        this.mockMvc
                .perform(get("/journeys/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.journeys", hasSize(expectedJourneys.size())))
                .andExpect(jsonPath("$.totalPages", is(expectedTotalPages)))
                .andExpect(jsonPath("$.journeys[0].departureStationId", is(Integer.parseInt(Long.toString(expectedJourneys.get(0).getDepartureStationId())))));
    }

    @AfterEach
    void emptyRepository() {
        repository.deleteAll();
    }
}
