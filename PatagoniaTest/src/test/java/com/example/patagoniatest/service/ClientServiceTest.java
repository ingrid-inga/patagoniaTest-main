package com.example.patagoniatest.service;

import com.example.patagoniatest.model.Client;
import com.example.patagoniatest.repository.ClientRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;
    private ClientService serviceUnderTest;

    @BeforeEach
    void setUp(){
        serviceUnderTest = new ClientService(clientRepository);
    }

    @Test
    void getClients() {
        serviceUnderTest.getClients();
        verify(clientRepository).findAll();
    }

    @Test
    void addClient() {
        Client client = new Client(
                456465l,
                "Esteban Dido",
                123123
        );

        serviceUnderTest.addClient(client);

        ArgumentCaptor<Client> clientArgumentCaptor =
                ArgumentCaptor.forClass(Client.class);

        verify(clientRepository).save(clientArgumentCaptor.capture());

        Client capturedClient = clientArgumentCaptor.getValue();

        assertThat(capturedClient).isEqualTo(client);
    }

    @Test
    @Disabled
    void deleteClient() {
    }

    @Test
    @Disabled
    void getClient() {
    }

    @Test
    @Disabled
    void updateClient() {
    }
}