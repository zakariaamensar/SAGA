package com.microservice.inventory.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.microservice.inventory.DTO.Inventory;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, String> {

}
