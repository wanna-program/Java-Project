package com.travelcompany.eshop.services;

import com.travelcompany.eshop.exceptions.CustomerNotFoundException;
import com.travelcompany.eshop.exceptions.ItineraryNotFoundException;
import com.travelcompany.eshop.models.*;

import java.util.ArrayList;
import java.util.List;

public class TicketServiceImpl implements TicketService {
    private List<Ticket> tickets = new ArrayList<>();
    private CustomerService customerService;
    private ItineraryService itineraryService;

    public TicketServiceImpl(CustomerService customerService, ItineraryService itineraryService) {
        this.customerService = customerService;
        this.itineraryService = itineraryService;
    }

    @Override
    public void purchaseTicket(Customer customer, Itinerary itinerary, PaymentMethod paymentMethod)
            throws CustomerNotFoundException, ItineraryNotFoundException {
        if (customer == null) {
            throw new CustomerNotFoundException("Customer not found.");
        }
        if (itinerary == null) {
            throw new ItineraryNotFoundException("Itinerary not found.");
        }

        double finalPrice = calculateFinalPrice(customer, itinerary.getBasicPrice(), paymentMethod);
        Ticket ticket = new Ticket(customer, itinerary, paymentMethod, finalPrice);
        tickets.add(ticket);
    }

    private double calculateFinalPrice(Customer customer, double basicPrice, PaymentMethod paymentMethod) {
        double price = basicPrice;
        // Apply customer category discount or surcharge
        if (customer.getCategory() == CustomerCategory.BUSINESS) {
            price *= 0.9; // 10% discount
        } else if (customer.getCategory() == CustomerCategory.INDIVIDUAL) {
            price *= 1.2; // 20% surcharge
        }
        // Apply payment method discount
        if (paymentMethod == PaymentMethod.CREDIT_CARD) {
            price *= 0.9; // 10% discount
        }
        return price;
    }

    @Override
    public List<Ticket> getTickets() {
        return tickets;
    }
}
}