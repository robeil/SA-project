package com.robeil.customersservice.service.impl;

import com.robeil.customersservice.integration.ChangeEvent;
import com.robeil.customersservice.integration.Sender;
import com.robeil.customersservice.model.Customer;
import com.robeil.customersservice.service.dto.CustomerDTO;
import com.robeil.customersservice.repository.CustomerRepository;
import com.robeil.customersservice.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private Sender sender;

    @Override
    public List<Customer> getAllCustomer() {
        var allCustomer =  customerRepository.findAll();
        return allCustomer;
//        return  allCustomer.stream()
//                .map(customer ->
//                    modelMapper.map(customer, CustomerDTO.class)
//                ).collect(Collectors.toList());
    }

    @Override
    public void addNewCustomer(Customer customer) {
        /**
         * Publishing added new customer
         */
        //modelMapper.map(customer,CustomerDTO.class)
        ChangeEvent<Customer> changeCustomer =
                new ChangeEvent<Customer>("addNewCustomer", customer);
        sender.send(changeCustomer);
        System.out.println("Publishing addNewCustomer from customerService");
        System.out.println(customer);
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(int customerNumber) {
        /***
         * Publishing deleted customer
         */
        Customer customerToDelete = customerRepository.findById(customerNumber).get();
        ChangeEvent<CustomerDTO> changeCustomer =
                new ChangeEvent("deleteCustomer", customerToDelete);
        sender.send(changeCustomer);
        System.out.println("Publishing deleteCustomer from customerService");
        System.out.println(customerToDelete);
        customerRepository.deleteById(customerNumber);
    }

    @Override
    public Customer updateCustomer(int customerNumber, Customer customer) {
        var customerToUpdate = customerRepository.findById(customerNumber).get();
        System.out.println("Publishing updateCustomer from customerService");
        System.out.println("From this ====> " + customerToUpdate);
        if(customerToUpdate != null){
            customerToUpdate.setCustomerNumber(customer.getCustomerNumber());
            customerToUpdate.setCustomerName(customer.getCustomerName());
            customerToUpdate.setAddress(customer.getAddress());
            customerToUpdate.setContact(customer.getContact());
            customerRepository.save(customerToUpdate);
        }
        /**
         * Publishing updated customer
         */
        ChangeEvent<Customer> changedCustomer =
                new ChangeEvent<Customer>("updateCustomer",customerToUpdate);
        sender.send(changedCustomer);
        System.out.println("To this ===> " + customer);
        return customerToUpdate; //modelMapper.map(customerToUpdate, CustomerDTO.class);
    }

    @Override
    public Customer getCustomerByCustomerNumber(int customerNumber) {
        var customer = customerRepository.findById(customerNumber);
        if(!customer.isPresent()){
            throw new RuntimeException("No Customer with this id");
        }
        return customer.get();
    }
}
