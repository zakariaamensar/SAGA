package com.amensar.facturation_microservice.service;

import com.amensar.facturation_microservice.model.Facturation;
import com.amensar.facturation_microservice.repo.FacturationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturationService {
    @Autowired
    private FacturationRepo repo;
    public void generateFacture(Facturation facture) {
        repo.save(facture);
    }

    public Facturation getFacture(int id) {
        return repo.findById(id).orElse(null);
    }

    public List<Facturation> getFactures() {
        return  repo.findAll();
    }

//    public Facturation getFactureByTacheId(int id) {
//        return repo.findByTacheId(id);
//    }

}
