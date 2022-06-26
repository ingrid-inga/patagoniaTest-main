package com.example.loanservice.controller;

import com.example.loanservice.entity.Loan;
import com.example.loanservice.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    LoanService loanService;

    @GetMapping
    public ResponseEntity<List<Loan>> getAll() {
        List<Loan> loans = loanService.getAll();
        if(loans.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Loan> getById(@PathVariable("id") int id) {
        Loan loan = loanService.getLoanById(id);
        if(loan == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(loan);
    }

    @PostMapping()
    public ResponseEntity<Loan> save(@RequestBody Loan loan) {
        Loan loanNew = loanService.save(loan);
        return ResponseEntity.ok(loanNew);
    }

    @GetMapping("/byclient/{clientId}")
    public ResponseEntity<List<Loan>> getByClientId(@PathVariable("clientId") int clientId) {
        List<Loan> loans = loanService.byClientId(clientId);
        if(loans.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(loans);
    }



}
