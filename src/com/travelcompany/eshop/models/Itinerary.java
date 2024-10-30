package com.travelcompany.eshop.models;


import java.time.LocalDateTime;

public class Itinerary {
    private int id;
    private String departureAirportCode;
    private String destinationAirportCode;
    private LocalDateTime departureDate;
    private String airline;
    private double basicPrice;

    public Itinerary(int id, String departureAirportCode, String destinationAirportCode, LocalDateTime departureDate, String airline, double basicPrice) {
        this.id = id;
        this.departureAirportCode = departureAirportCode;
        this.destinationAirportCode = destinationAirportCode;
        this.departureDate = departureDate;
        this.airline = airline;
        this.basicPrice = basicPrice;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public String getDestinationAirportCode() {
        return destinationAirportCode;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public String getAirline() {
        return airline;
    }

    public double getBasicPrice() {
        return basicPrice;
    }
}