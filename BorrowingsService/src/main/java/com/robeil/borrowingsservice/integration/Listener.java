package com.robeil.borrowingsservice.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robeil.borrowingsservice.model.Book;
import com.robeil.borrowingsservice.model.Borrower;
import com.robeil.borrowingsservice.model.Customer;
import com.robeil.borrowingsservice.service.BorrowerService;
import com.robeil.borrowingsservice.service.dto.BorrowerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class Listener {

    @Autowired
    private BorrowerService borrowerService;

    @KafkaListener(topics = {"final-customer-change-topic","final-book-change-topic"})
    public void receive(@Payload String changeEventAsString) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try{
            ChangeEvent changeEvent = objectMapper.readValue(changeEventAsString, ChangeEvent.class);

            if(changeEvent.getOperation().equals("addNewCustomer")){
                //Reading Customer Object
                ChangeEvent<Customer> customerAdded = objectMapper.readValue(
                        changeEventAsString,
                        new TypeReference<ChangeEvent<Customer>>() {});
                Customer customer = customerAdded.getDescription();

                //Reading Book Object
                ChangeEvent<Book> bookAdded = objectMapper.readValue(
                        changeEventAsString,
                        new TypeReference<ChangeEvent<Book>>() {});
                List<Book> books = Arrays.asList(bookAdded.getDescription());

                borrowerService.addNewBorrower(new Borrower(
                                customer.getCustomerNumber(),
                                LocalDate.now(),
                                customer,
                                books
                            )
                       );
                System.out.println("BorrowerService class Listener reading from the addedCustomer from Kafka");
            }

            if(changeEvent.getOperation().equals("deleteCustomer")){
                //Reading Customer Object
                ChangeEvent<Customer> customerToDelete = objectMapper.readValue(
                        changeEventAsString,
                        new TypeReference<ChangeEvent<Customer>>() {});
                int borrowerNumber = customerToDelete.getDescription().getCustomerNumber();
                borrowerService.deleteBorrower(borrowerNumber);
                System.out.println("Deleting borrower ");
            }

            if(changeEvent.getOperation().equals("updateCustomer")){
                //Reading Customer Object
                ChangeEvent<Customer> customerToUpdate = objectMapper.readValue(
                        changeEventAsString,
                        new TypeReference<ChangeEvent<Customer>>() {});
                Customer newCustomerDetail = customerToUpdate.getDescription();

                ChangeEvent<Book> bookAdded = objectMapper.readValue(
                        changeEventAsString,
                        new TypeReference<ChangeEvent<Book>>() {});
                List<Book> books = Arrays.asList(bookAdded.getDescription());

                int borrowerNumber = customerToUpdate.getDescription().getCustomerNumber();
                BorrowerDTO borrowerToUpdate = borrowerService.getBorrowerByNumber(borrowerNumber);

                borrowerService.updateBorrower(borrowerNumber,new Borrower(
                        borrowerToUpdate.getBorrowerNumber(),
                        borrowerToUpdate.getDate(),
                        newCustomerDetail,
                        books
                ));
                System.out.println("BorrowerService class Listener reading from  updatedCustomer from Kafka");
            }
            if(changeEvent.getOperation().equals("addNewBook")){
                ChangeEvent<Book> bookAdded = objectMapper.readValue(
                        changeEventAsString,
                        new TypeReference<ChangeEvent<Book>>() {});
                Book book = bookAdded.getDescription();
                //borrowerService.addBookToBorrower(book);
                System.out.println("BorrowerService class Listener reading from  addedBook from Kafka");
            }
            if(changeEvent.getOperation().equals("deleteBook")){
                ChangeEvent<Book> bookAdded = objectMapper.readValue(
                        changeEventAsString,
                        new TypeReference<ChangeEvent<Book>>() {});
                String bookIsbn = bookAdded.getDescription().getIsbn();
                borrowerService.removeBookFromBorrower(bookIsbn);
                System.out.println("BorrowerService class Listener reading from  deletedBook from Kafka");
            }

            if(changeEvent.getOperation().equals("updateBook")){
                ChangeEvent<Book> bookAdded = objectMapper.readValue(
                        changeEventAsString,
                        new TypeReference<ChangeEvent<Book>>() {});
                String bookIsbn = bookAdded.getDescription().getIsbn();
                Book book = bookAdded.getDescription();
                borrowerService.updateBorrowerBook(bookIsbn, book);
                System.out.println("BorrowerService class Listener reading from  updateBook from Kafka");
            }
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }
    }

}
