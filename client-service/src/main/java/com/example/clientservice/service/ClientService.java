package com.example.clientservice.service;

import com.example.clientservice.entity.Client;
import com.example.clientservice.feignclients.LoanFeignClient;
import com.example.clientservice.model.Loan;
import com.example.clientservice.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    LoanFeignClient loanFeignClient;

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    public void deleteClient(int id) {
        clientRepository.deleteById(id);
    }

    public Optional<Client> getClientById(int id) {
        return clientRepository.findById(id);
    }

    @Transactional
    public void updateClient(Client client, int id) {
        Client updatedClient = clientRepository.findById(id).orElseThrow(() -> new IllegalStateException(
                "client with id: " + id + "doesn´t exists"
        ));
        if (!Objects.equals(updatedClient.getFullName(), client.getFullName())){
            updatedClient.setFullName(client.getFullName());
        }
        if (updatedClient.getIncome() != client.getIncome()){
            updatedClient.setIncome(client.getIncome());
        }
    }

    public OptionalDouble getEarningsAverage() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                .mapToDouble(Client::getIncome)
                .average();
    }

    public List<Client> getTopEarners() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                .filter(client -> client.getIncome() >= 10000).collect(Collectors.toList());
    }

    public Loan saveLoan(int clientId, Loan loan) {
        loan.setClientId(clientId);
        Loan loanNew = loanFeignClient.save(loan);
        return loanNew;
    }

}
