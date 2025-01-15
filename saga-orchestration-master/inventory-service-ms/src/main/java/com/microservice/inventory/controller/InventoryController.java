package com.microservice.inventory.controller;

import org.springframework.web.bind.annotation.RestController;

import com.microservice.inventory.DTO.InventoryRequestDTO;
import com.microservice.inventory.DTO.InventoryResponseDTO;
import com.microservice.inventory.services.InventoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController("inventory")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @PostMapping("/deduct")
    public ResponseEntity<InventoryResponseDTO> deduct(@RequestBody InventoryRequestDTO requestBody) {
        InventoryResponseDTO inventoryResponse = inventoryService.deduct(requestBody);
        return ResponseEntity.ok().body(inventoryResponse);
    }

    @PostMapping("/add")
    public void add(@RequestBody InventoryRequestDTO requestBody) {
        inventoryService.add(requestBody);
    }

}
