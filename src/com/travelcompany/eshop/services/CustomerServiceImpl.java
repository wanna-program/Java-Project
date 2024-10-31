package com.travelcompany.eshop.services;

import com.travelcompany.eshop.exceptions.CustomerEmailException;
import com.travelcompany.eshop.exceptions.CustomerNotFoundException;
import com.travelcompany.eshop.models.Customer;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private List<Customer> customers;

    public CustomerServiceImpl(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public Customer findCustomerById(int id) throws CustomerNotFoundException {
        return customers.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found."));
    }

    @Override
    public void addCustomer(Customer customer) throws CustomerEmailException {
        if (customer.getEmail().endsWith("@travelcompany.com")) {
            throw new CustomerEmailException("Email cannot end with @travelcompany.com.");
        }
        customers.add(customer);
    }
}
