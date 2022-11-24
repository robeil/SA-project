package com.robeil.bookscommandservice.service;

import com.robeil.bookscommandservice.model.Book;
import com.robeil.bookscommandservice.service.dto.BookDTO;

public interface BookService {

    void addNewBook(Book book);
    void deleteBook(String isbn);
    BookDTO updateBook(String isbn, Book book);
}
