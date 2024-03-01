package com.library.libraryloanservice.controllers;

import com.library.libraryloanservice.models.Loan;
import com.library.libraryloanservice.services.LoanService;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/library/loans")
@Log4j
public class LoanController {
    @Autowired
    final LoanService service;
    final RestTemplate template;

    public LoanController(LoanService service) {
        this.service = service;
        this.template = new RestTemplate();
    }

    @GetMapping( path = "/",
            produces = "application/json"
    )
    public ResponseEntity<List<Loan>> getAllLoans() {
        var list = service.getAllEntries();
        if (!list.isEmpty()) return new ResponseEntity<>(list, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @SneakyThrows
    @GetMapping( path = "/{id}",
            produces = "application/json"
    )
    public ResponseEntity<Loan> getLoan(@PathVariable Long id) {
        var result = service.getEntryById(id);
        if (result != null) return new ResponseEntity<>(result, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @SneakyThrows
    @GetMapping( path = "users/{id}",
            produces = "application/json"
    )
    public ResponseEntity<Loan> getLoanByUserId(@PathVariable Long id) {
        var result = service.getLoanByUserId(id);
        if (result != null) return new ResponseEntity<>(result, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping( path = "/",
            consumes = "application/json",
            produces = "application/json"
    )
    public ResponseEntity<Loan> createLoan(@RequestBody Loan loan) {
        var result = service.create(loan);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @SneakyThrows
    @PostMapping( path = "/{id}",
            produces = "application/json"
    )
    public ResponseEntity<?> returnBook (@PathVariable Long id) {
        service.makeReturn(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
