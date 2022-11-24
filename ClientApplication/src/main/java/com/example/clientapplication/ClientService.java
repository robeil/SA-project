package com.example.clientapplication;

import com.example.clientapplication.domain.*;

import java.util.List;

public interface ClientService {
    /**
     * Borrowing
     */
    List<Borrower> getAllBorrowers();
    void addNewBorrower(Borrower borrower);
    void deleteBorrower(int borrowerNumber);
    void removeBookFromBorrower(String bookIsbn);
    Borrower getBorrowerByNumber(int borrowerNumber);
    void updateBorrowerBook(String bookIsbn, Book book);
    Borrower updateBorrower(int borrowerNumber, Borrower borrower);

    /**
     * Book query
     */
    Books getAllBooks();
    void addNewBook(Book book);
    void deleteBook(String bookTitle);
    Book updateBook(String isbn, Book book);
//    void addReviewToBook(String isbn, Review review);

    /**
     * Customer
     */
    Customers getAllCustomer();
    void addNewCustomer(Customer customer);
    void deleteCustomer(int customerNumber);
    Customer getCustomerByCustomerNumber(Integer customerNumber);
    Customer updateCustomer(int customerNumber, Customer customer);

    /**
     * Review
     */
    void addReviewToBook(Review review);

}
