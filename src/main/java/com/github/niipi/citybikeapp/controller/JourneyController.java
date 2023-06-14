package com.github.niipi.citybikeapp.controller;

import com.github.niipi.citybikeapp.model.Journey;
import com.github.niipi.citybikeapp.repository.JourneyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/journeys")
public class JourneyController {

    private static final Logger LOG = LoggerFactory.getLogger(JourneyController.class);

    @Autowired
    private JourneyRepository repository;

    private ControllerParameterValidation validation = new ControllerParameterValidation();

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
            if (LOG.isDebugEnabled()) {
                LOG.debug("/journeys/all has been called. Parametres: {}, {}", departureStationId, returnStationId);
            }
            List<Journey> journeys;
            page = validation.makeGivenParameterAcceptable(page, Integer.MAX_VALUE);
            size = validation.makeGivenParameterAcceptable(size);

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
            Map<String, Object> response = new HashMap<>();
            response.put("journeys", journeys);
            response.put("totalPages", journeyPage.getTotalPages());
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        } catch (Exception e) {
            LOG.error("An exception occurred.", e);
            Map<String, Object> emptyResponse = new HashMap<>();
            return ResponseEntity.badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(emptyResponse);
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Map<String, Long>> countJourneys(
            @RequestParam(value = "departureStationId", required = false)
            Long departureStationId,
            @RequestParam(value = "returnStationId", required = false)
            Long returnStationId) {
            Long countJourneys = 0l;
        try {
            if (departureStationId != null && returnStationId == null) {
                countJourneys = repository.countByDepartureStationId(departureStationId);
            } else if (departureStationId == null && returnStationId != null) {
                countJourneys = repository.countByReturnStationId(returnStationId);
            } else {
                countJourneys = repository.count();
            }
            Map<String, Long> response = new HashMap<>();
            response.put("countJourneys", countJourneys);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        } catch (Exception e) {
            LOG.error("An exeption occurred.", e);
            Map<String, Long> emptyResponse = new HashMap<>();
            return ResponseEntity.badRequest()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(emptyResponse);
        }
    }
}
