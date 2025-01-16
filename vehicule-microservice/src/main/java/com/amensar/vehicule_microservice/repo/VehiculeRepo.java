package com.amensar.vehicule_microservice.repo;

import com.amensar.vehicule_microservice.model.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculeRepo extends JpaRepository<Vehicule,Integer> {
}
