package com.robeil.bookscommandservice.service.impl;

import com.robeil.bookscommandservice.integration.ChangeEvent;
import com.robeil.bookscommandservice.integration.Sender;
import com.robeil.bookscommandservice.model.Book;
import com.robeil.bookscommandservice.repository.BookRepository;
import com.robeil.bookscommandservice.service.dto.BookDTO;
import com.robeil.bookscommandservice.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private Sender sender;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void addNewBook(Book book) {
        /**
         * publishing added new book
         */
        ChangeEvent<Book> changeBook = new ChangeEvent<Book>("addNewBook",book);
        sender.send(changeBook);
        System.out.println("Publishing addNewBook from BooksCommandService");
        System.out.println(book);
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(String isbn) {
        /**
         * publishing the deleted book
         */
        Book book = bookRepository.findBookByIsbn(isbn);
        ChangeEvent<Book> changedBook = new ChangeEvent<Book>("deleteBook",book);
        sender.send(changedBook);
        System.out.println("Publishing deleteBook from BooksCommandService");
        System.out.println(book);
        bookRepository.deleteByIsbn(isbn);
    }

    @Override
    public BookDTO updateBook(String isbn, Book book) {
        var bookToUpdate = bookRepository.findBookByIsbn(isbn);
        System.out.println("Publishing updateBook from BooksCommandService");
        System.out.println("From this ====> " + bookToUpdate);
        if(bookToUpdate != null){
            bookToUpdate.setIsbn(book.getIsbn());
            bookToUpdate.setBookTitle(book.getBookTitle());
            bookToUpdate.setAuthorName(book.getAuthorName());
            bookToUpdate.setDescription(book.getDescription());
            bookRepository.save(bookToUpdate);
        }
        /**
         * Publishing updated book
         */
        BookDTO bookDTO = modelMapper.map(bookToUpdate, BookDTO.class);
        ChangeEvent<Book> changedBook = new ChangeEvent<Book>("updateBook", book);
        sender.send(changedBook);
        System.out.println("To this ====> " + bookToUpdate);
        return bookDTO;
    }
}
