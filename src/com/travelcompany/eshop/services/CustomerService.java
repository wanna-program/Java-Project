package com.travelcompany.eshop.services;

import com.travelcompany.eshop.exceptions.CustomerEmailException;
import com.travelcompany.eshop.exceptions.CustomerNotFoundException;
import com.travelcompany.eshop.models.Customer;

public interface CustomerService {
    Customer findCustomerById(int id) throws CustomerNotFoundException;
    void addCustomer(Customer customer) throws CustomerEmailException;
}
