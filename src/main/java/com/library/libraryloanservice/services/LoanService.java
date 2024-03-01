package com.library.libraryloanservice.services;

import com.library.libraryloanservice.exceptions.NotFoundException;
import com.library.libraryloanservice.models.Loan;

public interface LoanService extends EntityService<Loan>{
    Loan getLoanByUserId(Long id) throws NotFoundException;

    void makeReturn(Long Id) throws NotFoundException;
}
