package com.example.patagoniatest.controller;

import com.example.patagoniatest.model.Client;
import com.example.patagoniatest.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
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
    public Optional<Client> getClient(@PathVariable Long id){
        return clientService.getClient(id);
    }
    @PostMapping("/addClient")
    public Client addClient(@RequestBody Client client){
        return clientService.addClient(client);
    }

    @DeleteMapping("/deleteClient/{id}")
    public void deleteClient(@PathVariable Long id){
        clientService.deleteClient(id);
    }

    @PutMapping("/updateClient/{id}")
    public void updateClient(@RequestBody Client client, @PathVariable Long id){
        clientService.updateClient(client, id);
    }
}
