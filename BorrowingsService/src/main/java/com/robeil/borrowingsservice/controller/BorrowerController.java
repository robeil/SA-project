package com.robeil.borrowingsservice.controller;

import com.robeil.borrowingsservice.model.Borrower;
import com.robeil.borrowingsservice.service.BorrowerService;
import com.robeil.borrowingsservice.service.dto.BorrowerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrows")
public class BorrowerController {

    @Autowired
    private BorrowerService borrowerService;

    @PostMapping("/add")
    public void borrowingNewBook(@RequestBody Borrower borrower){
        borrowerService.addNewBorrower(borrower);
    }

    @GetMapping("/get/{borrowerNumber}")
    public Borrower getBorrower(@PathVariable int borrowerNumber){
        return borrowerService.getBorrowerByNumber(borrowerNumber);
    }

    @GetMapping("/list")
    public List<Borrower> getAllBorrowers(){
        return borrowerService.getAllBorrowers();
    }

    @PutMapping("/update/{borrowerNumber}")
    public Borrower updateBorrower(@PathVariable int borrowerNumber, @RequestBody Borrower borrower){
        return borrowerService.updateBorrower(borrowerNumber,borrower);
    }

    @DeleteMapping("/delete/{borrowerNumber}")
    public void deleteBorrower(@PathVariable int borrowerNumber){
        borrowerService.deleteBorrower(borrowerNumber);
    }
}
