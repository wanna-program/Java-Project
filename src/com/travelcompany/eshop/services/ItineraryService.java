package com.travelcompany.eshop.services;

import com.travelcompany.eshop.exceptions.ItineraryNotFoundException;
import com.travelcompany.eshop.exceptions.InvalidAirportCodeException;
import com.travelcompany.eshop.models.Itinerary;

public interface ItineraryService {
    void addItinerary(Itinerary itinerary) throws InvalidAirportCodeException;
    Itinerary findItineraryById(int id) throws ItineraryNotFoundException;
}
