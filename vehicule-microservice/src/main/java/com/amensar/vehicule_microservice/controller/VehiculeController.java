package com.amensar.vehicule_microservice.controller;

import com.amensar.vehicule_microservice.model.Vehicule;
import com.amensar.vehicule_microservice.service.VehiculeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VehiculeController {
    @Autowired
    private VehiculeService service;

    @GetMapping("vehicules")
    public List<Vehicule> getVehicules(){
        return service.getVehicules();
    }

    @PostMapping("vehicules")
    public void addVehicule(@RequestBody Vehicule vehicule){
        service.addVehicule(vehicule);
    }

    @GetMapping("vehicules/{vin}")
    public Vehicule getVehicule(@PathVariable int vin){
        return service.getVehicule(vin);
    }

    @DeleteMapping("vehicules/{vin}/return")
    public Vehicule returnVehicule(@PathVariable  int vin){
        return service.returnVehicule(vin);
    }

    @PutMapping("vehicules/{vin}")
    public void updateVehicule(@RequestBody Vehicule vehicule){
        service.updateVehicule(vehicule);
    }
}
