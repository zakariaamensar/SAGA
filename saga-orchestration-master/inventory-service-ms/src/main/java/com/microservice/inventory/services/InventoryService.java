package com.microservice.inventory.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.inventory.DTO.Inventory;
import com.microservice.inventory.DTO.InventoryRequestDTO;
import com.microservice.inventory.DTO.InventoryResponseDTO;
import com.microservice.inventory.repository.InventoryRepository;
import com.microservice.inventory.utils.InventoryStatus;

@Service
public class InventoryService {
    @Autowired
    InventoryRepository inventoryRepository;

    public InventoryResponseDTO deduct(InventoryRequestDTO request) {
        Optional<Inventory> optionalProduct = inventoryRepository.findById(request.getProductId());
        InventoryResponseDTO inventoryResponse = new InventoryResponseDTO();
        inventoryResponse.setOrderId(request.getOrderId());
        inventoryResponse.setProductId(request.getProductId());
        inventoryResponse.setUserId(request.getUserId());
        inventoryResponse.setStatus(InventoryStatus.UNAVAILABLE);

        if (!optionalProduct.isPresent()) {
            return inventoryResponse;
        }

        Inventory product = optionalProduct.get();

        if (product.getQuantity() >= 1) {
            inventoryResponse.setPrice(product.getPrice());
            product.setQuantity(product.getQuantity() - 1);
            inventoryRepository.save(product);
            inventoryResponse.setStatus(InventoryStatus.AVAILABLE);
        }

        return inventoryResponse;
    }

    public void add(InventoryRequestDTO request) {
        Optional<Inventory> optionalProduct = inventoryRepository.findById(request.getProductId());
        if (optionalProduct.isPresent()) {
            Inventory product = optionalProduct.get();
            product.setQuantity(product.getQuantity() + 1);
            inventoryRepository.save(product);
        }
    }
}
