package com.microservice.payment.controller;

import org.springframework.web.bind.annotation.RestController;

import com.microservice.payment.DTO.PaymentRequestDTO;
import com.microservice.payment.DTO.PaymentResponseDTO;
import com.microservice.payment.services.paymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController("payment")
public class PaymentController {

    @Autowired
    paymentService inventoryService;

    @PostMapping("/debit")
    public ResponseEntity<PaymentResponseDTO> deduct(@RequestBody PaymentRequestDTO requestBody) {
        PaymentResponseDTO inventoryResponse = inventoryService.debit(requestBody);
        return ResponseEntity.ok().body(inventoryResponse);
    }

    @PostMapping("/credit")
    public void add(@RequestBody PaymentRequestDTO requestBody) {
        inventoryService.credit(requestBody);
    }
}
