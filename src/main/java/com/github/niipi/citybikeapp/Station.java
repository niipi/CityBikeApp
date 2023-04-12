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
    private int station_id;

    @Column(name = "nimi")
    private String station_name;

    @Column(name = "osoite")
    private String station_address;

    @Column(name = "kaupunki")
    private String station_city;

    @Column(name = "operaattor")
    private String service_provider;

    @Column(name = "kapasiteet")
    private int capacity;

   @Column(name = "x")
   private double latitude;

   @Column(name = "y")
   private double longitude;

}
