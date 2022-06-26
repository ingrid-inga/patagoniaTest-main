package com.example.clientservice.controller;

import com.example.clientservice.entity.Client;
import com.example.clientservice.model.Loan;
import com.example.clientservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@RestController
@RequestMapping("/client")
public class ClientController {


    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping()
    public List<Client> getClients(){
        return clientService.getClients();
    }

    @GetMapping("/getEarningsAverage")
    public OptionalDouble getEarningsAverage(){
        return clientService.getEarningsAverage();
    }

    @GetMapping("/getTopEarners")
    public List<Client> getTopEarners(){
        return clientService.getTopEarners();
    }

    @GetMapping("/{id}")
    public Optional<Client> getClient(@PathVariable int id){
        return clientService.getClientById(id);
    }

    @PostMapping()
    public Client addClient(@RequestBody Client client){
        return clientService.addClient(client);
    }

    @DeleteMapping("/deleteClient/{id}")
    public void deleteClient(@PathVariable int id){
        clientService.deleteClient(id);
    }

    @PutMapping("/updateClient/{id}")
    public void updateClient(@RequestBody Client client, @PathVariable int id){
        clientService.updateClient(client, id);
    }

    @PostMapping("/saveloan/{clientId}")
    public ResponseEntity<Loan> saveLoan(@PathVariable("clientId") int clientId, @RequestBody Loan loan) {
        if(clientService.getClientById(clientId) == null)
            return ResponseEntity.notFound().build();
        Loan loanNew = clientService.saveLoan(clientId, loan);
        return ResponseEntity.ok(loan);
    }

}
