package com.robeil.bookscommandservice.repository;

import com.robeil.bookscommandservice.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, Integer> {

    void deleteByIsbn(String isbn);
    Book findBookByIsbn(String isbn);

}
