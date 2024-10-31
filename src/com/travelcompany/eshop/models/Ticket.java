package com.travelcompany.eshop.models;

public class Ticket {
    private static int counter = 1;
    private int id;
    private Customer customer;
    private Itinerary itinerary;
    private PaymentMethod paymentMethod;
    private double paymentAmount;

    public Ticket(Customer customer, Itinerary itinerary, PaymentMethod paymentMethod, double paymentAmount) {
        this.id = counter++;
        this.customer = customer;
        this.itinerary = itinerary;
        this.paymentMethod = paymentMethod;
        this.paymentAmount = paymentAmount;
    }

    // Getters
    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Itinerary getItinerary() {
        return itinerary;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
}
