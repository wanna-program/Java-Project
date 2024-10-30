package com.travelcompany.eshop.models;

import com.travelcompany.eshop.exceptions.CustomerEmailException;

import java.util.Objects;

public class Customer extends Person {
    private CustomerCategory category;

    public Customer(int id, String name, String email, String address, String nationality, CustomerCategory category) throws CustomerEmailException {
        super(id, name, email, address, nationality);
        if (email != null && email.endsWith("@travelcompany.com")) {
            throw new CustomerEmailException("Email cannot be from travelcompany.com domain.");
        }
        this.category = category;
    }

    public CustomerCategory getCategory() {
        return category;
    }

    public void setCategory(CustomerCategory category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return getId() == customer.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}