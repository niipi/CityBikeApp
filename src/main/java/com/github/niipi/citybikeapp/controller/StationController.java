package com.github.niipi.citybikeapp.controller;


import com.github.niipi.citybikeapp.model.Station;
import com.github.niipi.citybikeapp.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/stations")
public class StationController {

    @Autowired
    private StationRepository repository;

    public StationController(StationRepository repository) {
        this.repository = repository;
    }

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
            List<Station> stations = new ArrayList<Station>();
            Pageable pageable = PageRequest.of(page, size);
            Page<Station> stationPage;
            if (stationName == null && stationAddress == null) {
                stationPage = repository.findAll(pageable);
            }
            else if (stationName == null) {
                stationPage = repository.findByStationAddress(stationAddress, pageable);
            }
            else if (stationAddress == null) {
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
            e.printStackTrace();
            Map<String, Object> emptyResponse = new HashMap<String, Object>();
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(emptyResponse);
        }
    }
}
