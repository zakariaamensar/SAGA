package com.amensar.tache_microservice.service;

import com.amensar.tache_microservice.kafkaProducer.MaintenanceProducer;
import com.amensar.tache_microservice.model.Tache;
import com.amensar.tache_microservice.repo.TacheRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TacheService {
    @Autowired
    private TacheRepo repo;

    @Autowired
    private MaintenanceProducer maintenanceProducer;

    public List<Tache> getTaches() {
        return repo.findAll();
    }


    public Tache getTache(int id) {
        return repo.findById(id).orElse(null);
    }

    public void addTache(Tache tache) {
        repo.save(tache);
    }

    public void modifyTache(Tache tache) {
        String maintenanceEvent = "{ \"taskId\": " + tache.getId() +
                ", \"status\": \"" + tache.getStatus() + "\"" +
                ", \"vehiculeId\": \"" + tache.getVehiculeId() + "\" }";

        maintenanceProducer.sendMaintenanceUpdate(maintenanceEvent);
        repo.save(tache);
    }

    public void deleteTache(int id) {
        repo.deleteById(id);
    }
}
