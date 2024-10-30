package com.travelcompany.eshop.services;

import com.travelcompany.eshop.models.Customer;

import java.util.List;

public interface ReportService {
    void generateTotalTicketsAndCostPerCustomer();

    void generateTopCustomersByTicketsAndCost();

    void generateCustomersWithNoTickets(List<Customer> allCustomers);

    void generateItinerariesReport();

    void generateTicketsWithDiscountDetails();

    void generateDestinationsPerCustomer();
}
