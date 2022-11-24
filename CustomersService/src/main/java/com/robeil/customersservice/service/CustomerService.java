package com.robeil.customersservice.service;

import com.robeil.customersservice.model.Customer;
import com.robeil.customersservice.service.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getAllCustomer();
    void addNewCustomer(Customer customer);
    void deleteCustomer(int customerNumber);
    CustomerDTO updateCustomer(int customerNumber, Customer customer);
    CustomerDTO getCustomerByCustomerNumber(int customerNumber);
}
