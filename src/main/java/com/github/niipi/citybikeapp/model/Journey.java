package com.github.niipi.citybikeapp.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "journeys")
public class Journey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int journeyId;

    @Column(name = "departure")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime departureTime;

    @Column(name = "return")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime returnTime;

    @Column(name = "departure_station_id")
    private int departureStationId;

    @Column(name = "departure_station_name")
    private String departureStationName;

    @Column(name = "return_station_id")
    private int returnStationId;

    @Column(name = "return_station_name")
    private String returnStationName;

    @Column(name = "distance")
    private int distance;

    @Column(name = "duration")
    private int duration;

    public Journey() {
        // empty constructor
    }

    public Journey(LocalDateTime departureTime, LocalDateTime returnTime, int departureStationId, String departureStationName,
                   int returnStationId, String returnStationName, int distance, int duration) {
        this.departureTime = departureTime;
        this.returnTime = returnTime;
        this.departureStationId = departureStationId;
        this.departureStationName = departureStationName;
        this.returnStationId = returnStationId;
        this.returnStationName = returnStationName;
        this.distance = distance;
        this.duration = duration;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(LocalDateTime returnTime) {
        this.returnTime = returnTime;
    }

    public int getDepartureStationId() {
        return departureStationId;
    }

    public void setDepartureStationId(int departureStationId) {
        this.departureStationId = departureStationId;
    }

    public String getDepartureStationName() {
        return departureStationName;
    }

    public void setDepartureStationName(String departureStationName) {
        this.departureStationName = departureStationName;
    }

    public int getReturnStationId() {
        return returnStationId;
    }

    public void setReturnStationId(int returnStationId) {
        this.returnStationId = returnStationId;
    }

    public String getReturnStationName() {
        return returnStationName;
    }

    public void setReturnStationName(String returnStationName) {
        this.returnStationName = returnStationName;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Journey{" +
                "journeyId=" + journeyId +
                ", departureTime=" + departureTime +
                ", returnTime=" + returnTime +
                ", departureStationId=" + departureStationId +
                ", departureStationName='" + departureStationName + '\'' +
                ", returnStationId=" + returnStationId +
                ", returnStationName='" + returnStationName + '\'' +
                ", distance=" + distance +
                ", duration=" + duration +
                '}';
    }
}
