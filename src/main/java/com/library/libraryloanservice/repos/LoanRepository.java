package com.library.libraryloanservice.repos;

import com.library.libraryloanservice.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    @Query("select l from Loan l where l.userId = ?1")
    Loan getLoanByUserId(Long userId);
}