package com.robeil.booksqueryservice.controller;

import com.robeil.booksqueryservice.model.Book;
import com.robeil.booksqueryservice.service.BookService;
import com.robeil.booksqueryservice.service.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bookquery")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/list")
    public List<Book> getBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/get/{isbn}")
    public Book getBookByIsbn(@PathVariable String isbn){
      return bookService.getBookByIsbn(isbn);
    }
}
