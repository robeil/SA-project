package com.robeil.booksqueryservice.service.impl;

import com.robeil.booksqueryservice.model.Book;
import com.robeil.booksqueryservice.model.Review;
import com.robeil.booksqueryservice.repository.BookRepository;
import com.robeil.booksqueryservice.service.dto.BookDTO;
import com.robeil.booksqueryservice.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<BookDTO> getAllBooks() {
        var allBooks = bookRepository.findAll();

        return allBooks.stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addNewBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public BookDTO updateBook(String isbn, Book book) {
        var bookToUpdate = bookRepository.findBookByIsbn(isbn);
        if(bookToUpdate != null){
            bookToUpdate.setBookTitle(bookToUpdate.getBookTitle());
            bookToUpdate.setIsbn(book.getIsbn());
            bookToUpdate.setReviews(book.getReviews());
            bookToUpdate.setAuthorName(book.getAuthorName());
            bookToUpdate.setDescription(book.getDescription());
            bookRepository.save(bookToUpdate);
        }
        return modelMapper.map(bookToUpdate, BookDTO.class);
    }

    @Override
    public void deleteBook(String bookTitle) {
        bookRepository.deleteByBookTitle(bookTitle);
    }

    @Override
    public void addReviewToBook(String isbn, Review review) {
        Book book = bookRepository.findBookByIsbn(isbn);
        if(book != null){
            List<Review> reviews = book.getReviews();
            reviews.add(review);
            bookRepository.save(book);
        }
    }

    @Override
    public BookDTO getBookByIsbn(String isbn) {
        return modelMapper.map(bookRepository.findBookByIsbn(isbn), BookDTO.class);
    }
}
