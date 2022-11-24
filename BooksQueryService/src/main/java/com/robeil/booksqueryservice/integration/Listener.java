package com.robeil.booksqueryservice.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.robeil.booksqueryservice.model.Book;
import com.robeil.booksqueryservice.model.Review;
import com.robeil.booksqueryservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class Listener {

    @Autowired
    private BookService bookService;

    @KafkaListener(topics = {"final-book-change-topic","final-review-change-topic"})
    public void receive(@Payload String changeEventAsString) {

        ObjectMapper objectMapper = new ObjectMapper();
        //objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        try{
            ChangeEvent changeEvent = objectMapper.readValue(changeEventAsString, ChangeEvent.class);

            if(changeEvent.getOperation().equals("addNewBook")){
                ChangeEvent<Book> bookChange = objectMapper.readValue(changeEventAsString,
                        new TypeReference<ChangeEvent<Book>>() {});
                Book book = bookChange.getDescription();
                bookService.addNewBook(book);
                System.out.println("BookQueryService Listener reading the addedNewBook from Kafka");
            }

            if(changeEvent.getOperation().equals("updateBook")){
                ChangeEvent<Book> bookChange = objectMapper.readValue(changeEventAsString,
                        new TypeReference<ChangeEvent<Book>>() {});
                Book book = bookChange.getDescription();
                bookService.updateBook(book.getIsbn(), book);
                System.out.println("BookQueryService Listener reading the updatedBook from Kafka");
            }

            if(changeEvent.getOperation().equals("deleteBook")){
                ChangeEvent<Book> bookChange = objectMapper.readValue(changeEventAsString,
                        new TypeReference<ChangeEvent<Book>>() {});
                String bookTitle = bookChange.getDescription().getBookTitle();
                bookService.deleteBook(bookTitle);
                System.out.println("BookQueryService Listener reading the deletedBook from Kafka");
            }

            if(changeEvent.getOperation().equals("addReview")){
                ChangeEvent<Book> bookChange = objectMapper.readValue(changeEventAsString,
                        new TypeReference<ChangeEvent<Book>>() {});

                ChangeEvent<Review> addReview = objectMapper.readValue(changeEventAsString,
                        new TypeReference<ChangeEvent<Review>>() {});
                String isbn = addReview.getDescription().getIsbn();
                Review review = addReview.getDescription();
                bookService.addReviewToBook(isbn, review);
                System.out.println("BookQueryService Listener reading the addReview from Kafka");
            }

        } catch (JsonProcessingException e){
            e.printStackTrace();
        }
    }
}
