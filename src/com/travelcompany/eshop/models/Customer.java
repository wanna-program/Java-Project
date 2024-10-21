package com.travelcompany.eshop.models;

public class Customer extends Person {
    private CustomerCategory category;

    public Customer(int id, String name, String email, String address, String nationality, CustomerCategory category) {
        super(id, name, email, address, nationality);
        this.category = category;
    }

    public CustomerCategory getCategory() {
        return category;
    }

    public void setCategory(CustomerCategory category) {
        this.category = category;
    }
}


