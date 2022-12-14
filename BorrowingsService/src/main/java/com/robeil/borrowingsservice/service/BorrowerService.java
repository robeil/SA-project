package com.robeil.borrowingsservice.service;

import com.robeil.borrowingsservice.model.Book;
import com.robeil.borrowingsservice.model.Borrower;
import com.robeil.borrowingsservice.model.Customer;
import com.robeil.borrowingsservice.service.dto.BorrowerDTO;

import java.util.List;

public interface BorrowerService {

    List<BorrowerDTO> getAllBorrowers();
    void addNewBorrower(Borrower borrower);
    void deleteBorrower(int borrowerNumber);
    void removeBookFromBorrower(String bookIsbn);
    BorrowerDTO getBorrowerByNumber(int borrowerNumber);
    void updateBorrowerBook(String bookIsbn, Book book);
    BorrowerDTO updateBorrower(int borrowerNumber, Borrower borrower);
    BorrowerDTO handleBorrowerDetails(int borrowerNumber, Customer customer, List<Book> books);


}
