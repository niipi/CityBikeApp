package com.github.niipi.citybikeapp.controller;

import com.github.niipi.citybikeapp.Station;
import com.github.niipi.citybikeapp.repository.StationRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@RunWith(SpringRunner.class)
class StationControllerTest extends ControllerTest {

    @Autowired
    private StationRepository repository;

    private Station testStation1 = new Station(001, "Testiasema1", "Testikuja 15", "Vantaa", "CityBike Finland", 35, 22.4556, 33.5678);
    private Station testStation2 = new Station(002, "Teststation2", "Testpromenad 7", "Espoo", "CityBike Finland", 44, 11.3466, 33.6785);

    /** Populates test station repository with test stations provided. **/
    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext) {
        repository.save(testStation1);
        repository.save(testStation2);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new StationController(repository)).build();
    }

    /** Compares values from test station repository get response to expected values. **/
    @Test
    void getAllStations() throws Exception {
        ArrayList<Station> expectedStations = new ArrayList<>();
        expectedStations.add(testStation1);
        expectedStations.add(testStation2);
        int expectedTotalPages = 1;
        this.mockMvc
                .perform(get("/stations/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.stations", hasSize(expectedStations.size())))
                .andExpect(jsonPath("$.totalPages", is(expectedTotalPages)))
                .andExpect(jsonPath("$.stations[0].stationId", is(expectedStations.get(0).getStationId())))
                .andExpect(jsonPath("$.stations[1].stationId", is(expectedStations.get(1).getStationId())));
    }

    @AfterEach
    void emptyRepository() {
        repository.deleteAll();
    }
}