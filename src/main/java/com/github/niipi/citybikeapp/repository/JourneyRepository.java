package com.github.niipi.citybikeapp.repository;

import com.github.niipi.citybikeapp.model.Journey;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JourneyRepository extends JpaRepository<Journey, Integer> {

    Page<Journey> findByDepartureStationId(Long departureStationId, Pageable pageable);

    Page<Journey> findByReturnStationId(Long returnStationId, Pageable pageable);

    Page<Journey> findByDepartureStationIdAndReturnStationId(Long departureStationId, Long returnStationId, Pageable pageable);

}
