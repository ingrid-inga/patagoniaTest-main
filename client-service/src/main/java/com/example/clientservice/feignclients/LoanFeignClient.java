package com.example.clientservice.feignclients;


import com.example.clientservice.model.Loan;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="loan-service")
@RequestMapping("/loan")
public interface LoanFeignClient {

    @PostMapping()
    Loan save(@RequestBody Loan loan);


    @GetMapping("/byclient/{clientId}")
    List<Loan> getLoans(@PathVariable("clientId")int clientId);
}
