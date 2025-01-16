package com.amensar.garage.service;

import com.amensar.garage.model.Client;
import com.amensar.garage.repo.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepo repo;
    public Client getClient(int id) {
        return repo.findById(id).orElse(new Client());
    }

    public void add(Client client) {
        repo.save(client);
    }

    public List<Client> getAllClients() {
        return repo.findAll();
    }

    public void updateClient(Client client) {
        repo.save(client);
    }

    public void delete(int id) {
        repo.deleteById(id);
    }

    public List<Client> searchClient(String keyword) {
        return repo.findByEmailContaining(keyword);
    }
}
