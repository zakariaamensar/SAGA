package com.amensar.facturation_microservice.controller;

import com.amensar.facturation_microservice.model.Facturation;
import com.amensar.facturation_microservice.service.FacturationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class FacturationController {
    @Autowired
    private FacturationService service;

    @PostMapping("facturations")
    public void generateFacture(@RequestBody Facturation facture){
        service.generateFacture(facture);
    }

    @GetMapping("facturations/{id}")
    public Facturation getFacture(@PathVariable int id){
        return service.getFacture(id);
    }

    @GetMapping("facturations")
    public List<Facturation> getFactures(){
        return service.getFactures();
    }

//    @GetMapping("facturations/{tacheId}")
//    public Facturation getFactureByTacheId(@PathVariable("tacheId") int id){
//        return service.getFactureByTacheId(id);
//    }


}
