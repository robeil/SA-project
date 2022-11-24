package com.example.clientapplication;

import com.example.clientapplication.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    private RestTemplate restTemplate;
    /**
     * borrowerService URL
     */
    private final String borrowerUrl = "http://localhost:2005/borrows/add";
    private final String borrowerUrlGet = "http://localhost:2005/borrows/get/{borrowerNumber}";
    private final String borrowerURLList = "http://localhost:2005/borrows/list";
    private final String borrowerURLDelete = "http://localhost:2005/borrows/delete/{borrowerNumber}";
    private final String borrowerURLUpdate = "http://localhost:2005/borrows/update";
    /**
     * customerService URL
     */
    private final String customerUrl = "http://localhost:2001/customers";
    private final String customerUrlUpdate = "http://localhost:2001/customers/update/{customerNumber}";
    private final String customerUrlGet = "http://localhost:2001/customers/get/{customerNumber}";
    private final String customerUtlDelete = "http://localhost:2001/customers/delete/{customerNumber}";
    private final String customerUrlAdd = "http://localhost:2001/customers/add";
    /**
     * reviewService URL
     */
    private final String reviewUrlAdd = "http://localhost:2006/reviews/add";
    /**
     * bookCommandService URL
     */
    private final String bookCommandUrlDelete = "http://localhost:2002/bookcommand/delete/{borrowerNumber}";
    private final String bookCommandUrlAdd = "http://localhost:2002/bookcommand/add";
    private final String bookCommandUrlUpdate = "http://localhost:2002/bookcommand/update/{isbn}";

    /**
     * bookQueryService URL
     */
    private final String bookQueryUrl = "http://localhost:2003/bookquery/list";
    private final String bookQueryUrlGet = "http://localhost:2003/bookquery/get/{isbn}";

    @Override
    public List<Borrower> getAllBorrowers() {
        Borrower[] allBorrowers = restTemplate.getForObject(borrowerURLList, Borrower[].class);
        return (List<Borrower>) Arrays.asList(allBorrowers);
    }

    @Override
    public void addNewBorrower(Borrower borrower) {
        URI uri = restTemplate.postForLocation(borrowerUrl,borrower,Borrower.class);
    }

    @Override
    public void deleteBorrower(int borrowerNumber) {
        restTemplate.delete(borrowerURLDelete,borrowerNumber);
    }

    @Override
    public void removeBookFromBorrower(String bookIsbn) {
        restTemplate.delete(borrowerURLDelete,bookIsbn);
    }

    @Override
    public Borrower getBorrowerByNumber(int borrowerNumber) {
        var borrower = restTemplate.getForObject(borrowerUrlGet, Borrower.class,borrowerNumber);
        return borrower;
    }

    @Override
    public void updateBorrowerBook(String bookIsbn, Book book) {
       restTemplate.put(borrowerURLUpdate+"/"+bookIsbn,book,Borrower.class);
    }

    @Override
    public Borrower updateBorrower(int borrowerNumber, Borrower borrower) {
         restTemplate.postForLocation(borrowerURLUpdate+"/"+borrower,Borrower.class);
        return borrower;
    }

    @Override
    public Books getAllBooks() {
        var books = restTemplate.getForObject(bookQueryUrl,Books.class);
        return books;
    }

    @Override
    public void addNewBook(Book book) {
        URI uri = restTemplate.postForLocation(bookCommandUrlAdd,book,Book.class);
    }

    @Override
    public void deleteBook(String bookTitle) {
        restTemplate.delete(bookCommandUrlDelete,bookTitle);
    }

    @Override
    public Book updateBook(String isbn, Book book) {
        restTemplate.put(bookCommandUrlUpdate+"/"+isbn,book,Book.class);
        return book;
    }

//    @Override
//    public void addReviewToBook(String isbn, Review review) {
//        URI uri = restTemplate.postForLocation(reviewUrlAdd,review,Review.class);
//    }

    @Override
    public Customers getAllCustomer() {
        var allCustomer = restTemplate.getForObject(customerUrl,Customers.class);
        return allCustomer;
    }

    @Override
    public void addNewCustomer(Customer customer) {
        URI uri = restTemplate.postForLocation(customerUrlAdd, customer, Customer.class);
    }

    @Override
    public void deleteCustomer(int customerNumber) {
        restTemplate.delete(customerUtlDelete + customerNumber);
    }

    @Override
    public Customer getCustomerByCustomerNumber(Integer customerNumber) {
        var customer = restTemplate.getForObject(customerUrlGet+"/"+customerNumber, Customer.class);
        return customer;
    }

    @Override
    public Customer updateCustomer(int customerNumber, Customer customer) {
        restTemplate.put(customerUrlUpdate+"/"+customerNumber,customer,Customer.class);
        return customer;
    }

    @Override
    public void addReviewToBook(Review review) {
        restTemplate.postForLocation(reviewUrlAdd,review,Review.class);
    }

}
