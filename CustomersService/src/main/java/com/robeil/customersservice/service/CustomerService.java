package com.robeil.customersservice.service;

import com.robeil.customersservice.model.Customer;
import com.robeil.customersservice.service.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomer();
    void addNewCustomer(Customer customer);
    void deleteCustomer(int customerNumber);
    Customer updateCustomer(int customerNumber, Customer customer);
    Customer getCustomerByCustomerNumber(int customerNumber);
}
