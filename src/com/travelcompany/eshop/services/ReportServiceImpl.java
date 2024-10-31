package com.travelcompany.eshop.services;

import com.travelcompany.eshop.models.*;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class ReportServiceImpl implements ReportService {
    private List<Ticket> tickets;
    private List<Itinerary> itineraries;
    private List<Customer> customers;

    public ReportServiceImpl(List<Ticket> tickets, List<Itinerary> itineraries, List<Customer> customers) {
        this.tickets = tickets;
        this.itineraries = itineraries;
        this.customers = customers;
    }

    @Override
    public void generateCustomerReport() {
        System.out.println("Customer Report:");
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.printf("| %-3s | %-25s | %-25s | %-15s | %-10s | %-10s |\n", "ID", "Name", "Email", "Address", "Nationality", "Category");
        System.out.println("--------------------------------------------------------------------------------------------");

        for (Customer customer : customers) {
            System.out.printf("| %-3d | %-25s | %-25s | %-15s | %-10s | %-10s |\n",
                    customer.getId(),
                    customer.getName(),
                    customer.getEmail(),
                    customer.getAddress(),
                    customer.getNationality(),
                    customer.getCategory());
        }

        System.out.println("--------------------------------------------------------------------------------------------\n");
    }

    @Override
    public void generateItineraryReport() {
        System.out.println("Itinerary Report:");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-3s | %-5s | %-5s | %-20s | %-15s | %-10s | %-15s |\n", "ID", "From", "To", "Departure Date", "Airline", "Price", "Basic Price");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        for (Itinerary itinerary : itineraries) {
            System.out.printf("| %-3d | %-5s | %-5s | %-20s | %-15s | %-10.2f | %-15.2f |\n",
                    itinerary.getId(),
                    itinerary.getDepartureAirportCode(),
                    itinerary.getDestinationAirportCode(),
                    itinerary.getDepartureDate().format(formatter),
                    itinerary.getAirline(),
                    itinerary.getBasicPrice(),
                    itinerary.getBasicPrice());
        }

        System.out.println("---------------------------------------------------------------------------------------------------------------------------\n");
    }

    @Override
    public void generateOrderedTicketsReport() {
        System.out.println("Ordered Tickets Report:");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-3s | %-25s | %-5s | %-5s | %-20s | %-10s | %-10s |\n", "ID", "Customer", "From", "To", "Departure Date", "Price", "Payment");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        tickets.sort(Comparator.comparingInt(Ticket::getId));

        for (Ticket ticket : tickets) {
            Itinerary itinerary = ticket.getItinerary();
            System.out.printf("| %-3d | %-25s | %-5s | %-5s | %-20s | %-10.2f | %-10s |\n",
                    ticket.getId(),
                    ticket.getCustomer().getName(),
                    itinerary.getDepartureAirportCode(),
                    itinerary.getDestinationAirportCode(),
                    itinerary.getDepartureDate().format(formatter),
                    ticket.getPaymentAmount(),
                    ticket.getPaymentMethod());
        }

        System.out.println("---------------------------------------------------------------------------------------------------------------------------\n");
    }

    @Override
    public void generateTotalTicketsAndCostPerCustomer() {
        System.out.println("Total Tickets and Cost per Customer:");
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.printf("| %-25s | %-10s | %-15s |\n", "Customer", "Tickets", "Total Cost");
        System.out.println("--------------------------------------------------------------------------------------------");

        Map<Customer, List<Ticket>> ticketsByCustomer = tickets.stream()
                .collect(Collectors.groupingBy(Ticket::getCustomer));

        for (Customer customer : ticketsByCustomer.keySet()) {
            List<Ticket> customerTickets = ticketsByCustomer.get(customer);
            double totalCost = customerTickets.stream()
                    .mapToDouble(Ticket::getPaymentAmount)
                    .sum();

            System.out.printf("| %-25s | %-10d | %-15.2f |\n",
                    customer.getName(),
                    customerTickets.size(),
                    totalCost);
        }

        System.out.println("--------------------------------------------------------------------------------------------\n");
    }

    @Override
    public void generateTotalItinerariesPerDestinationAndDeparture() {
        System.out.println("Total Itineraries per Destination and Departure:");
        System.out.println("---------------------------------------------------------");
        System.out.printf("| %-15s | %-15s | %-10s |\n", "Departure", "Destination", "Total");
        System.out.println("---------------------------------------------------------");

        Map<String, Map<String, Long>> itinerariesCount = itineraries.stream()
                .collect(Collectors.groupingBy(
                        Itinerary::getDepartureAirportCode,
                        Collectors.groupingBy(
                                Itinerary::getDestinationAirportCode,
                                Collectors.counting()
                        )
                ));

        for (String departure : itinerariesCount.keySet()) {
            Map<String, Long> destinations = itinerariesCount.get(departure);
            for (String destination : destinations.keySet()) {
                System.out.printf("| %-15s | %-15s | %-10d |\n",
                        departure,
                        destination,
                        destinations.get(destination));
            }
        }

        System.out.println("---------------------------------------------------------\n");
    }

    @Override
    public void generateTopCustomersByTicketsAndCost() {
        System.out.println("Top Customers by Number of Tickets and Total Cost:");
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.printf("| %-25s | %-10s | %-15s |\n", "Customer", "Tickets", "Total Cost");
        System.out.println("--------------------------------------------------------------------------------------------");

        Map<Customer, List<Ticket>> ticketsByCustomer = tickets.stream()
                .collect(Collectors.groupingBy(Ticket::getCustomer));

        List<Map.Entry<Customer, List<Ticket>>> sortedCustomers = ticketsByCustomer.entrySet().stream()
                .sorted((e1, e2) -> {
                    int compare = Integer.compare(e2.getValue().size(), e1.getValue().size());
                    if (compare == 0) {
                        double totalCost1 = e1.getValue().stream().mapToDouble(Ticket::getPaymentAmount).sum();
                        double totalCost2 = e2.getValue().stream().mapToDouble(Ticket::getPaymentAmount).sum();
                        return Double.compare(totalCost2, totalCost1);
                    }
                    return compare;
                })
                .collect(Collectors.toList());

        for (Map.Entry<Customer, List<Ticket>> entry : sortedCustomers) {
            Customer customer = entry.getKey();
            List<Ticket> customerTickets = entry.getValue();
            double totalCost = customerTickets.stream()
                    .mapToDouble(Ticket::getPaymentAmount)
                    .sum();

            System.out.printf("| %-25s | %-10d | %-15.2f |\n",
                    customer.getName(),
                    customerTickets.size(),
                    totalCost);
        }

        System.out.println("--------------------------------------------------------------------------------------------\n");
    }

    @Override
    public void generateCustomersWithNoTickets(List<Customer> allCustomers) {
        System.out.println("Customers with No Tickets:");
        System.out.println("---------------------------------------------------------");
        System.out.printf("| %-3s | %-25s |\n", "ID", "Customer");
        System.out.println("---------------------------------------------------------");

        Set<Customer> customersWithTickets = tickets.stream()
                .map(Ticket::getCustomer)
                .collect(Collectors.toSet());

        for (Customer customer : allCustomers) {
            if (!customersWithTickets.contains(customer)) {
                System.out.printf("| %-3d | %-25s |\n",
                        customer.getId(),
                        customer.getName());
            }
        }

        System.out.println("---------------------------------------------------------\n");
    }

    @Override
    public void generateItinerariesReport() {

    }

    @Override
    public void generateTicketsWithDiscountDetails() {

    }

    @Override
    public void generateDestinationsPerCustomer() {

    }
}

