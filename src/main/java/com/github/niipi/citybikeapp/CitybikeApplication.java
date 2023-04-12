package com.github.niipi.citybikeapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

import java.util.List;

@SpringBootApplication
public class CitybikeApplication {

	private Logger logger = LoggerFactory.getLogger(Controller.class);

	@Autowired
	private StationRepository repository;

	@EventListener(ApplicationReadyEvent.class)
	public void runAfterStartup() {
		List allStations = this.repository.findAll();
		logger.info("Number of stations: " + allStations.size());
	}

	public static void main(String[] args) {
		SpringApplication.run(CitybikeApplication.class, args);
	}

}
