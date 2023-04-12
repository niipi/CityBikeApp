package com.github.niipi.citybikeapp;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;

public interface StationRepository extends JpaRepository<Station, Integer> {

    Page<Station> findByStationName(String stationName, Pageable pageable);

    Page<Station> findByStationAddress(String stationAddress, Pageable pageable);

    Page<Station> findByStationNameAndStationAddress(String stationName, String stationAddress, Pageable pageable);
}
