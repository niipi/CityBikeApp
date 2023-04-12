package com.github.niipi.citybikeapp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


/** Class for generating station objects based on information from the PostGreSQL database table "stations". **/
@Entity
@Table(name = "stations")
public class Station {

    @Id
    private int stationId;

    @Column(name = "nimi")
    private String stationName;

    @Column(name = "osoite")
    private String stationAddress;

    @Column(name = "kaupunki")
    private String stationCity;

    @Column(name = "operaattori")
    private String serviceProvider;

    @Column(name = "kapasiteetti")
    private Integer capacity;

   @Column(name = "x")
   private double latitude;

   @Column(name = "y")
   private double longitude;

   public Station() {
       // empty constructor
   }

   public Station(int stationId, String stationName, String stationAddress, String stationCity, String serviceProvider, Integer capacity, double latitude, double longitude) {
       this.stationId = stationId;
       this.stationName = stationName;
       this.stationAddress = stationAddress;
       this.stationCity = stationCity;
       this.serviceProvider = serviceProvider;
       if (capacity != null) {
           this.capacity = capacity;
       }
       else {
           this.capacity = 0;
       }
       this.latitude = latitude;
       this.longitude = longitude;
   }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String station_name) {
        this.stationName = station_name;
    }

    public String getStationAddress() {
        return stationAddress;
    }

    public void setStationAddress(String stationAddress) {
        this.stationAddress = stationAddress;
    }

    public String getStationCity() {
        return stationCity;
    }

    public void setStationCity(String stationCity) {
        this.stationCity = stationCity;
    }

    public String getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(String serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        if (capacity != null) {
            this.capacity = capacity;
        }
        else {
            this.capacity = 0;
        }
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


}
