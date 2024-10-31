package com.travelcompany.eshop.main;

import com.travelcompany.eshop.exceptions.CustomerEmailException;
import com.travelcompany.eshop.exceptions.InvalidAirportCodeException;
import com.travelcompany.eshop.models.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataLoader {
    private List<Customer> customers = new ArrayList<>();
    private List<Itinerary> itineraries = new ArrayList<>();

    // List of valid airport codes
    private List<String> validAirportCodes = Arrays.asList("ATH", "PAR", "LON", "AMS", "DUB", "FRA", "MEX", "NYC");

    public DataLoader() {
        initializeCustomers();
        initializeItineraries();
    }

    private void initializeCustomers() {
        try {
            customers.add(new Customer(1, "Dimitris Zachos", "dzachos@mail.com", "Athens", "Greek", CustomerCategory.INDIVIDUAL));
            customers.add(new Customer(2, "Dimitris Paschalis", "dpaschalis@mail.com", "Hong Kong", "Chinese", CustomerCategory.INDIVIDUAL));
            customers.add(new Customer(3, "Thomas Karavas", "tkaravas@mail.com", "Athens", "Greek", CustomerCategory.BUSINESS));
            customers.add(new Customer(4, "Dora Vasiladioti", "dvasiladioti@mail.com", "Washington DC", "USA", CustomerCategory.INDIVIDUAL));
            customers.add(new Customer(5, "Panagiota Plati", "pplati@mail.com", "Athens", "Greek", CustomerCategory.INDIVIDUAL));

            // Customers who haven't purchased any tickets
            customers.add(new Customer(6, "Spyros Farantos", "sfarantos@mail.com", "Saint Petersburg Russia", "Russian", CustomerCategory.BUSINESS));
            customers.add(new Customer(7, "Michail Vazakopoulos", "mvazako@mail.com", "Lausanne", "Swiss", CustomerCategory.BUSINESS)); // This will throw exception
            customers.add(new Customer(8, "Xlapatsas Xlapatsidis", "xlapatsas@mail.com", "Aeroporia Athens", "Greek", CustomerCategory.INDIVIDUAL));
            customers.add(new Customer(9, "Monica Belluci", "monica@mail.com", "Milan", "Italian", CustomerCategory.BUSINESS));
        } catch (CustomerEmailException e) {
            System.err.println("Error creating customer: " + e.getMessage());
        }
    }

    private void initializeItineraries() {
        try {
            itineraries.add(createItinerary(1, "PAR", LocalDateTime.of(2024, 11, 1, 13, 35), "SkyLines", 300.00));
            itineraries.add(createItinerary(2, "LON", LocalDateTime.of(2024, 11, 1, 13, 40), "Aegean", 420.00));
            itineraries.add(createItinerary(3, "AMS", LocalDateTime.of(2024, 11, 1, 13, 45), "AirBahar", 280.00));
            itineraries.add(createItinerary(4, "PAR", LocalDateTime.of(2024, 11, 1, 14, 20), "RyanAir", 310.00));
            itineraries.add(createItinerary(5, "DUB", LocalDateTime.of(2024, 11, 1, 14, 45), "SkyLines", 340.00));
            itineraries.add(createItinerary(6, "DUB", LocalDateTime.of(2024, 11, 1, 15, 10), "SkyLines", 350.00));
            itineraries.add(createItinerary(7, "FRA", LocalDateTime.of(2024, 11, 1, 15, 35), "Lufthansa", 420.00));
            itineraries.add(createItinerary(8, "FRA", LocalDateTime.of(2024, 11, 1, 16, 00), "Lufthansa", 430.00));
            itineraries.add(createItinerary(9, "MEX", LocalDateTime.of(2024, 11, 1, 16, 25), "Emirates", 800.00));
            itineraries.add(createItinerary(10, "NYC", LocalDateTime.of(2024, 11, 1, 17, 00), "Emirates", 935.00));
        } catch (InvalidAirportCodeException e) {
            System.err.println("Error creating itinerary: " + e.getMessage());
        }
    }

    private Itinerary createItinerary(int id, String destinationCode, LocalDateTime departureTime, String airline, double basicPrice) throws InvalidAirportCodeException {
        String departureCode = "ATH"; // Departure is always ATH
        if (!validAirportCodes.contains(departureCode)) {
            throw new InvalidAirportCodeException("Invalid departure airport code: " + departureCode);
        }
        if (!validAirportCodes.contains(destinationCode)) {
            throw new InvalidAirportCodeException("Invalid destination airport code: " + destinationCode);
        }
        return new Itinerary(id, departureCode, destinationCode, departureTime, airline, basicPrice);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Itinerary> getItineraries() {
        return itineraries;
    }
}
