package com.robeil.customersservice.controller;

import com.robeil.customersservice.model.Customer;
import com.robeil.customersservice.service.CustomerService;
import com.robeil.customersservice.service.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/add")
    public void addNewCustomer(@RequestBody Customer customer){
        customerService.addNewCustomer(customer);
    }
    @GetMapping("/list")
    public List<Customer> getCustomer(){
        return customerService.getAllCustomer();
    }

    @GetMapping("/get/{customerNumber}")
    public Customer getCustomerByNumber(@PathVariable int customerNumber){
        return customerService.getCustomerByCustomerNumber(customerNumber);

    }

    @PutMapping("/update/{customerNumber}")
    public Customer updateCustomer(@PathVariable Integer customerNumber, @RequestBody Customer customer){
        return customerService.updateCustomer(customerNumber,customer);
    }
    @DeleteMapping("/delete/{customerNumber}")
    public void deleteCustomer(@PathVariable int customerNumber){
        customerService.deleteCustomer(customerNumber);
    }
}
