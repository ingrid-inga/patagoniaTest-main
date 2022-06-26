package com.example.loanservice.service;

import com.example.loanservice.entity.Loan;
import com.example.loanservice.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    @Autowired
    LoanRepository loanRepository;

    public List<Loan> getAll() {
        return loanRepository.findAll();
    }

    public Loan getLoanById(int id) {
        return loanRepository.findById(id).orElse(null);
    }

    public Loan save(Loan loan) {
        Loan loanNew = loanRepository.save(loan);
        return loanNew;
    }

    public List<Loan> byClientId(int clientId) {
        return loanRepository.findByClientId(clientId);
    }
}
