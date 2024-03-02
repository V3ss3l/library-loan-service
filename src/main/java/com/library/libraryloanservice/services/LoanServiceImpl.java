package com.library.libraryloanservice.services;

import com.library.libraryloanservice.exceptions.NotFoundException;
import com.library.libraryloanservice.models.Loan;
import com.library.libraryloanservice.repos.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    private final LoanRepository repository;


    public LoanServiceImpl(LoanRepository repository) {
        this.repository = repository;
    }
    @Override
    public List<Loan> getAllEntries() {
        return repository.findAll();
    }
    @Override
    public Loan getEntryById(Long id) throws NotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Loan by this |"+id+"| not found in database"));
    }

    @Override
    public Loan getLoanByUserId(Long id) throws NotFoundException {
        var result =  repository.getLoanByUserId(id);
        if (result == null ) throw new NotFoundException("Loan by this user |"+id+"| not found in database");
        else return result;
    }
    @Override
    public Loan create(Loan obj) {
        return repository.save(obj);
    }
    @Override
    public void delete(Long id) throws NotFoundException{
        repository.findById(id).ifPresent(result -> repository.deleteById(id));
    }

    @Override
    public void makeReturn(Long id) throws NotFoundException {
        var loan = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Loan by this |"+id+"| not found in database"));
        loan.setReturned(true);
        repository.saveAndFlush(loan);
    }
}
