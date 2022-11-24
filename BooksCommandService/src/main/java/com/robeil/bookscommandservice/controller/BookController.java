package com.robeil.bookscommandservice.controller;

import com.robeil.bookscommandservice.integration.ChangeEvent;
import com.robeil.bookscommandservice.model.Book;
import com.robeil.bookscommandservice.service.BookService;
import com.robeil.bookscommandservice.service.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.DELETE;

@RestController
@RequestMapping("/bookcommand")
public class BookController {

    @Autowired

    private BookService bookService;

    @PostMapping("/add")
    public void addNewBook(@RequestBody Book book){
        bookService.addNewBook(book);
    }

    @PutMapping("update/{isbn}")
    public BookDTO updateBook(@PathVariable String isbn, @RequestBody Book book){
        return bookService.updateBook(isbn,book);
    }

    @DeleteMapping("/delete/{isbn}")
    public void deleteBook(@PathVariable String isbn) {
        bookService.deleteBook(isbn);
    }
}
