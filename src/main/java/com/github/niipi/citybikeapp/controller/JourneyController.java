package com.github.niipi.citybikeapp.controller;

import com.github.niipi.citybikeapp.model.Journey;
import com.github.niipi.citybikeapp.repository.JourneyRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/journeys")
public class JourneyController {

    @Autowired
    private JourneyRepository repository;

    public JourneyController(JourneyRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllJourneys(
            @RequestParam(value = "departureStationId", required = false)
            Long departureStationId,
            @RequestParam(value = "returnStationId", required = false)
            Long returnStationId,
            @RequestParam(value = "page", defaultValue = "0")
            int page,
            @RequestParam(value = "size", defaultValue = "10")
            int size) {
        try {
            List<Journey> journeys = new ArrayList<Journey>();
            // TODO: check page and size
            Pageable pageable = PageRequest.of(page, size);
            Page<Journey> journeyPage;
            if (departureStationId != null && returnStationId != null) {
                journeyPage = repository.findByDepartureStationIdAndReturnStationId(departureStationId, returnStationId, pageable);
            }
            else if (departureStationId != null && returnStationId == null) {
                journeyPage = repository.findByDepartureStationId(departureStationId, pageable);
            }
            else if (returnStationId != null && departureStationId == null) {
                journeyPage = repository.findByReturnStationId(returnStationId, pageable);
            }
            else {
                journeyPage = repository.findAll(pageable);
            }
            journeys = journeyPage.getContent();
            Map<String, Object> response = new HashMap<String, Object>();
            response.put("journeys", journeys);
            response.put("totalPages", journeyPage.getTotalPages());
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        } catch (Exception e) {
            // TODO: add logging framework
            e.printStackTrace();
            Map<String, Object> emptyResponse = new HashMap<String, Object>();
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(emptyResponse);
        }
    }
}
