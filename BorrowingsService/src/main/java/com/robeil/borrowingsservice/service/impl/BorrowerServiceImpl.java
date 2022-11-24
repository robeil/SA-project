package com.robeil.borrowingsservice.service.impl;

import com.robeil.borrowingsservice.model.Book;
import com.robeil.borrowingsservice.model.Borrower;
import com.robeil.borrowingsservice.model.Customer;
import com.robeil.borrowingsservice.repository.BorrowerRepository;
import com.robeil.borrowingsservice.service.dto.BookDTO;
import com.robeil.borrowingsservice.service.dto.BorrowerDTO;
import com.robeil.borrowingsservice.service.BorrowerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowerServiceImpl implements BorrowerService {

    @Autowired
    private BorrowerRepository borrowerRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<BorrowerDTO> getAllBorrowers() {
        var allBorrowers = borrowerRepository.findAll();

        return allBorrowers.stream()
                .map(borrower -> modelMapper.map(borrower, BorrowerDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addNewBorrower(Borrower borrower) {
        borrowerRepository.save(borrower);
    }

    @Override
    public void deleteBorrower(int borrowerNumber) {
        borrowerRepository.findByBorrowerNumber(borrowerNumber);
    }

    @Override
    public BorrowerDTO updateBorrower(int borrowerNumber, Borrower borrower) {
        var borrowerToUpdate = borrowerRepository.findByBorrowerNumber(borrowerNumber);

        if(borrowerToUpdate != null){
            borrowerToUpdate.setBorrowerNumber(borrower.getBorrowerNumber());
            borrowerToUpdate.setDate(borrower.getDate());
            borrowerToUpdate.setBooks(borrower.getBooks());
            borrowerToUpdate.setCustomer(borrower.getCustomer());
            borrowerRepository.save(borrowerToUpdate);
        }
        return modelMapper.map(borrowerToUpdate, BorrowerDTO.class);
    }

    @Override
    public BorrowerDTO getBorrowerByNumber(int borrowerNumber) {
        var borrower = borrowerRepository.findByBorrowerNumber(borrowerNumber);
        return modelMapper.map(borrower, BorrowerDTO.class);
    }

    @Override
    public void updateBorrowerBook(String bookIsbn, Book book) {
        List<Borrower> borrowers = borrowerRepository.findAll();

        borrowers.stream()
                .flatMap(borrower -> borrower.getBooks().stream())
                .filter(boo -> boo.getIsbn().equals(book.getIsbn()))
                .map(update -> {
                    update.setIsbn(book.getIsbn());
                    update.setBookTitle(book.getBookTitle());
                    return update;
                }).collect(Collectors.toList());

    }

    /**
     * Handling the data the came from kafka
     */
    @Override
    public BorrowerDTO handleBorrowerDetails(int borrowerNumber, Customer customer, List<Book> books){
        Borrower borrower = borrowerRepository.findByBorrowerNumber(borrowerNumber);
        borrower.setCustomer(customer);
        borrower.setBooks(books);
        return modelMapper.map(borrower, BorrowerDTO.class);
    }

    @Override
    public void removeBookFromBorrower(String bookIsbn) {
        List<Borrower> borrowers = borrowerRepository.findAll();
        borrowers.stream()
                .flatMap(borrower -> borrower.getBooks().stream())
                .filter(boo -> boo.getIsbn().equals(bookIsbn))
                .map(update -> {
                    update.setIsbn(null);
                    update.setBookTitle(null);
                    return update;
                }).collect(Collectors.toList());
    }
}
