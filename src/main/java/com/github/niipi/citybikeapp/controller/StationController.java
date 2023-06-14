package com.github.niipi.citybikeapp.controller;

import com.github.niipi.citybikeapp.model.Station;
import com.github.niipi.citybikeapp.repository.StationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;
import java.util.*;

@Controller
@RequestMapping("/stations")
public class StationController {

    private final static Logger LOG = LoggerFactory.getLogger(StationController.class);

    @Autowired
    private StationRepository repository;

    public StationController(StationRepository repository) {
        this.repository = repository;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllStations(
            @RequestParam(value = "name", required = false)
            String stationName,
            @RequestParam(value = "address", required = false)
            String stationAddress,
            @RequestParam(value = "page", defaultValue = "0")
            int page,
            @RequestParam(value = "size", defaultValue = "10")
            int size) {

        try {
            if (LOG.isDebugEnabled()) {
                LOG.debug("/stations/all has been called. Parametres: " + stationName + stationAddress);
            }
            List<Station> stations = new ArrayList<Station>();
            page = makeGivenParameterAcceptable(page, Integer.MAX_VALUE);
            size = makeGivenParameterAcceptable(size);
            Pageable pageable = PageRequest.of(page, size);
            Page<Station> stationPage;

            if (stationName == null && stationAddress == null) {
                stationPage = repository.findAll(pageable);
            }
            else if (stationName == null && stationAddress != null) {
                stringParameterIsValid("stationAddress", stationAddress);
                stationPage = repository.findByStationAddress(stationAddress, pageable);
            }
            else if (stationAddress == null && stationName != null) {
                stringParameterIsValid("stationName", stationName);
                stationPage = repository.findByStationName(stationName, pageable);
            }
            else {
                stationPage = repository.findByStationNameAndStationAddress(stationName, stationAddress, pageable);
            }
            stations = stationPage.getContent();
            Map<String, Object> response = new HashMap<String, Object>();
            response.put("stations", stations);
            response.put("totalPages", stationPage.getTotalPages());
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(response);
        } catch (Exception e) {
            LOG.error("An exception occurred.", e);
            Map<String, Object> emptyResponse = new HashMap<String, Object>();
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(emptyResponse);
        }
    }

    private int makeGivenParameterAcceptable(int parameter) {
        return makeGivenParameterAcceptable(parameter, 100);
    }

    private int makeGivenParameterAcceptable(int parameter, int maxValue) {
        int acceptableParameter;
        if (parameter < 0) {
            acceptableParameter = 0;
        }
        else if (parameter > maxValue) {
            acceptableParameter = 100;
        }
        else {
            acceptableParameter = parameter;
        }
        return acceptableParameter;
    }

    private void stringParameterIsValid(String parameterName, String parameterValue) {
        // Acceptable string patterns contain alphanumeric characters, - and parentheses, and are between 1-256 characters long
        String pattern = "^[a-zA-Z0-9() åäöÅÄÖ-]{1,255}$";
        if (!parameterValue.matches(pattern)) {
            throw new InvalidParameterException("Invalid parameter for station request was given: " + parameterName + " = " + parameterValue);
        }
    }
}
