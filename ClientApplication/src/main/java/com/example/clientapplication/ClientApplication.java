package com.example.clientapplication;


import com.example.clientapplication.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ClientApplication implements CommandLineRunner {

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
    @Autowired
    ClientServiceImpl clientserviceImpl;

    private final String borrowerUrl = "http://localhost:2005/borrows";

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        /**
         * Creating contact
         */
        Contact contact1 = new Contact("123-456-7890","robeilaregawi@miu.edu");
        Contact contact2 = new Contact("890-456-4321","merontekle@miu.edu");

        /**
         * Creating Address
         */
        Address address1 = new Address("176th street","Chicago","9890");
        Address address2 = new Address("671th street","New York","2342");

        /**
         * Creating Customers
         */
        Customer customer1 = new Customer(111,"Robeil Aregawi",contact1,address1);
        Customer customer2 = new Customer(222,"Meron Tekle",contact2,address2);
        System.out.println("******************************** adding new customer **************************************");
        clientserviceImpl.addNewCustomer(customer1);
        clientserviceImpl.addNewCustomer(customer2);
        System.out.println(customer1.toString());
        System.out.println(customer2.toString());

        /**
         *Creating Books
         */
        Book book1 = new Book("i121","Java","Spring Boot for Beginners","Marting Fowler",null);
        Book book2 = new Book("i122","JavaScript","Function for Beginners","Brendon js",null);
        List<Book> bookList1 = new ArrayList<>();
        bookList1.add(book1);
        bookList1.add(book2);
        System.out.println("**************************************adding new books ************************************");
        clientserviceImpl.addNewBook(book1);
        clientserviceImpl.addNewBook(book2);
        System.out.println(book1);
        System.out.println(book2);
        System.out.println("**************************************update books ************************************");

//        clientserviceImpl.updateBook(book1.getIsbn(),
//                new Book(book1.getIsbn(),"updated-1","Python","John def",
//                        Arrays.asList(
//                                new Review(15,2,book1.getIsbn())
//                        )));

        /**
         * Creating Reviews
         */
        Review review1 = new Review(01,5,book1.getIsbn());
        Review review2 = new Review(02,4,book2.getIsbn());
        List<Review> reviewList1 = new ArrayList<>();
        reviewList1.add(review1);
        reviewList1.add(review2);
        System.out.println("**********************************adding review to the book *******************************");
        clientserviceImpl.addReviewToBook(review1);
        clientserviceImpl.addReviewToBook(review2);
        System.out.println(review1);
        System.out.println(review2);


        Review review3 = new Review(03,3,book1.getIsbn());
        Review review4 = new Review(04,4,book2.getIsbn());
        List<Review> reviewList2 = new ArrayList<>();
        reviewList2.add(review3);
        reviewList2.add(review4);
        clientserviceImpl.addReviewToBook(review3);
        clientserviceImpl.addReviewToBook(review4);
        System.out.println(review3);
        System.out.println(review4);

        /**
         * Creating Borrowers
         */
        Borrower borrower1 = new Borrower(111, LocalDate.now(),customer1,bookList1);
        Borrower borrower2 = new Borrower(222, LocalDate.now(),customer2,bookList1);
        System.out.println("**********************************Adding borrower *****************************************");
        clientserviceImpl.addNewBorrower(borrower1);
        clientserviceImpl.addNewBorrower(borrower2);
        System.out.println(borrower1);
        System.out.println(borrower2);

        System.out.println("************************************* Get all Borrowers ***********************************");
        List<Borrower> borrowers = clientserviceImpl.getAllBorrowers();
        for(Borrower borrower: borrowers){
            System.out.println(borrower.toString());
        }
    }
}
