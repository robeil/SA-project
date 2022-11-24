package com.robeil.booksqueryservice.service;

import com.robeil.booksqueryservice.model.Book;
import com.robeil.booksqueryservice.model.Review;
import com.robeil.booksqueryservice.service.dto.BookDTO;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();
    void addNewBook(Book book);
    Book getBookByIsbn(String isbn);
    void deleteBook(String bookTitle);
    Book updateBook(String isbn, Book book);
    void addReviewToBook(String isbn, Review review);


}
