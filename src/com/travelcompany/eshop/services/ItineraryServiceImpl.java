package com.travelcompany.eshop.services;

import com.travelcompany.eshop.exceptions.InvalidAirportCodeException;
import com.travelcompany.eshop.exceptions.ItineraryNotFoundException;
import com.travelcompany.eshop.models.Itinerary;

import java.util.Arrays;
import java.util.List;

public class ItineraryServiceImpl implements ItineraryService {
    private List<Itinerary> itineraries;

    // List of valid airport codes
    private List<String> validAirportCodes = Arrays.asList("ATH", "PAR", "LON", "AMS",
            "DUB", "FRA", "MEX", "NYC");

    public ItineraryServiceImpl(List<Itinerary> itineraries) {
        this.itineraries = itineraries;
    }

    @Override
    public void addItinerary(Itinerary itinerary) throws InvalidAirportCodeException {
        if (!validAirportCodes.contains(itinerary.getDepartureAirportCode())
                || !validAirportCodes.contains(itinerary.getDestinationAirportCode())) {
            throw new InvalidAirportCodeException("Invalid airport code in itinerary ID: "
                    + itinerary.getId());
        }
        itineraries.add(itinerary);
    }

    @Override
    public Itinerary findItineraryById(int id) throws ItineraryNotFoundException {
        return itineraries.stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElseThrow(() -> new ItineraryNotFoundException("Itinerary with ID " + id + " not found."));
    }
}
