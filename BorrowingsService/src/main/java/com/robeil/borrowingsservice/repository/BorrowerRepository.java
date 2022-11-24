package com.robeil.borrowingsservice.repository;

import com.robeil.borrowingsservice.model.Borrower;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowerRepository extends MongoRepository<Borrower, Integer> {

    Borrower findByBorrowerNumber(int borrowerNumber);
}
