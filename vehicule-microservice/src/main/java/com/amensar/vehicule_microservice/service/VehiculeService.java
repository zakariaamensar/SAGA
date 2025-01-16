package com.amensar.vehicule_microservice.service;

import com.amensar.vehicule_microservice.model.Vehicule;
import com.amensar.vehicule_microservice.repo.VehiculeRepo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VehiculeService {

    @Autowired
    private VehiculeRepo repo;
    public List<Vehicule> getVehicules() {
        return repo.findAll();
    }

    public Vehicule getVehicule(int vin) {
        return repo.findById(vin).orElse(null);
    }

    public void addVehicule(Vehicule vehicule) {
        repo.save(vehicule);
    }

    public Vehicule returnVehicule(int vin) {
        Vehicule vehicule  = repo.findById(vin).orElse(null);
        if(vehicule != null){
            repo.deleteById(vin);
        }
        return vehicule;
    }

    public void updateVehicule(Vehicule vehicule) {
        repo.save(vehicule);
    }

    @KafkaListener(topics = "maintenance-updated", groupId = "service-vehicule-group")
    public void consume(String maintenanceEvent) {
        System.out.println("Événement reçu : " + maintenanceEvent);

        try {
            // Parse the JSON event
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode eventNode = objectMapper.readTree(maintenanceEvent);

            // Extract fields from the event
            String vehiculeId = eventNode.get("vehiculeId").asText();
            String status = eventNode.get("status").asText();

            // Find the vehicle by ID and update its status
            Vehicule vehicule = repo.findById(Integer.parseInt(vehiculeId)).orElse(null);

            if (vehicule != null) {
                vehicule.setStatus(status);
                repo.save(vehicule);
                System.out.println("Statut du véhicule mis à jour : " + vehicule);
            } else {
                System.err.println("Véhicule introuvable pour l'ID : " + vehiculeId);
            }
        } catch (Exception e) {
            System.err.println("Erreur lors du traitement de l'événement de maintenance : " + e.getMessage());
        }
    }
}
