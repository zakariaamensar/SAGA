package com.amensar.tache_microservice.controller;

import com.amensar.tache_microservice.model.Tache;
import com.amensar.tache_microservice.service.TacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TacheController {
    @Autowired
    private TacheService service;

    @GetMapping("/maintenance/taches")
    public List<Tache> getTaches(){
        return service.getTaches();
    }

    @GetMapping("/maintenance/taches/{id}")
    public  Tache getTache(@PathVariable int id){
        return service.getTache(id);
    }

    @PostMapping("/maintenance/taches")
    public void addTache(@RequestBody Tache tache){
        service.addTache(tache);
    }

    @PutMapping("/maintenance/taches")
    public void modifyTache(@RequestBody Tache tache){
        service.modifyTache(tache);
    }

    @DeleteMapping("/maintenance/taches/{id}")
    public void deleteTache(@PathVariable int id){
        service.deleteTache(id);
    }
}
