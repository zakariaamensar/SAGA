package com.amensar.tache_microservice.repo;

import com.amensar.tache_microservice.model.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TacheRepo extends JpaRepository<Tache,Integer> {
}
