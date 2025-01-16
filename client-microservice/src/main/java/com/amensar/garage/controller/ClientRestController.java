package com.amensar.garage.controller;

import com.amensar.garage.model.Client;
import com.amensar.garage.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientRestController {
    @Autowired
    ClientService service;

    @PostMapping("clients")
    public void addClient(@RequestBody Client client){
        service.add(client);
    }

    @GetMapping("clients")
    public List<Client> getClients(){
        return service.getAllClients();
    }

    @GetMapping("clients/{id}")
    public Client getClient(@PathVariable int id){
        return service.getClient(id);
    }

    @PutMapping("clients")
    public void updateClient(@RequestBody Client client){
        service.updateClient(client);
    }

    @DeleteMapping("clients/{id}")
    public void deleteClient(@PathVariable int id){
        service.delete(id);
    }

    @GetMapping("clients/search")
    public List<Client> searchClient(@RequestParam("keyword") String keyword){
        System.out.println(keyword);
        return service.searchClient(keyword);
    }
}
