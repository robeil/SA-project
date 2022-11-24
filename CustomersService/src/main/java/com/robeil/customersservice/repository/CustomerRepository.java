package com.robeil.customersservice.repository;

import com.robeil.customersservice.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, Integer> {
}
