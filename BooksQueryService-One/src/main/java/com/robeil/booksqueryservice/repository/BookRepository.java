package com.robeil.booksqueryservice.repository;

import com.robeil.booksqueryservice.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    Book findBookByIsbn(String isbn);
    void deleteByBookTitle(String bookTitle);

}
