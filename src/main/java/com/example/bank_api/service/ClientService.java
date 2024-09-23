package com.example.bank_api.service;

import com.example.bank_api.model.Client;
import com.example.bank_api.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(UUID id) {
        return clientRepository.findById(id).orElse(null);
    }

    public Client saveClient(Client client) {
//        client.setId(UUID.randomUUID());
        return clientRepository.save(client);
    }

    public void deleteClient(UUID id) {
        clientRepository.deleteById(id);
    }
}
