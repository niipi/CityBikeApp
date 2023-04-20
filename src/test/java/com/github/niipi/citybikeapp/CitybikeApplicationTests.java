package com.github.niipi.citybikeapp;

import com.github.niipi.citybikeapp.controller.HomeController;
import com.github.niipi.citybikeapp.controller.StationController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CitybikeApplicationTests {

	@Autowired
	private HomeController homeController;

	@Autowired
	private StationController stationController;


	@Test
	void contextLoads() throws Exception {
		assertThat(homeController).isNotNull();
		assertThat(stationController).isNotNull();
	}

}
