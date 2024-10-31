package com.travelcompany.eshop.main;

import com.travelcompany.eshop.exceptions.CustomerNotFoundException;
import com.travelcompany.eshop.exceptions.ItineraryNotFoundException;
import com.travelcompany.eshop.models.*;
import com.travelcompany.eshop.services.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Initialize data
        DataLoader dataLoader = new DataLoader();
        List<Customer> customers = dataLoader.getCustomers();
        List<Itinerary> itineraries = dataLoader.getItineraries();

        // Initialize services
        CustomerService customerService = new CustomerServiceImpl(customers);
        ItineraryService itineraryService = new ItineraryServiceImpl(itineraries);
        TicketService ticketService = new TicketServiceImpl(customerService, itineraryService);
        ReportService reportService = new ReportServiceImpl(ticketService.getTickets(), itineraries, customers);

        // Simulate ticket purchases
        try {
            ticketService.purchaseTicket(customerService.findCustomerById(1), itineraryService.findItineraryById(2), PaymentMethod.CASH);
            ticketService.purchaseTicket(customerService.findCustomerById(2), itineraryService.findItineraryById(3), PaymentMethod.CASH);
            ticketService.purchaseTicket(customerService.findCustomerById(3), itineraryService.findItineraryById(3), PaymentMethod.CREDIT_CARD);
            ticketService.purchaseTicket(customerService.findCustomerById(2), itineraryService.findItineraryById(4), PaymentMethod.CREDIT_CARD);
            ticketService.purchaseTicket(customerService.findCustomerById(3), itineraryService.findItineraryById(4), PaymentMethod.CASH);
            ticketService.purchaseTicket(customerService.findCustomerById(4), itineraryService.findItineraryById(7), PaymentMethod.CREDIT_CARD);
            ticketService.purchaseTicket(customerService.findCustomerById(5), itineraryService.findItineraryById(7), PaymentMethod.CREDIT_CARD);
            ticketService.purchaseTicket(customerService.findCustomerById(2), itineraryService.findItineraryById(10), PaymentMethod.CASH);
            ticketService.purchaseTicket(customerService.findCustomerById(1), itineraryService.findItineraryById(3), PaymentMethod.CASH);
        } catch (CustomerNotFoundException | ItineraryNotFoundException e) {
            System.err.println("Error purchasing ticket: " + e.getMessage());
        }

        // Generate reports
        System.out.println("Generating reports...\n");
        reportService.generateCustomerReport();
        reportService.generateItineraryReport();
        reportService.generateOrderedTicketsReport();
        reportService.generateTotalTicketsAndCostPerCustomer();
        reportService.generateTotalItinerariesPerDestinationAndDeparture();
        reportService.generateTopCustomersByTicketsAndCost();
        reportService.generateCustomersWithNoTickets(customers);
    }
}
