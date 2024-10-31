package com.travelcompany.eshop.services;

import com.travelcompany.eshop.exceptions.CustomerNotFoundException;
import com.travelcompany.eshop.exceptions.ItineraryNotFoundException;
import com.travelcompany.eshop.models.Customer;
import com.travelcompany.eshop.models.Itinerary;
import com.travelcompany.eshop.models.PaymentMethod;
import com.travelcompany.eshop.models.Ticket;

import java.util.List;

public interface TicketService {
    void purchaseTicket(Customer customer, Itinerary itinerary, PaymentMethod paymentMethod)
            throws CustomerNotFoundException, ItineraryNotFoundException;
    List<Ticket> getTickets();
}
