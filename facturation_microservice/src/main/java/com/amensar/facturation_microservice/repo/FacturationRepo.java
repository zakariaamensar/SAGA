package com.amensar.facturation_microservice.repo;

import com.amensar.facturation_microservice.model.Facturation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturationRepo extends JpaRepository<Facturation,Integer> {
//    Facturation findByTacheId(int id);
}
