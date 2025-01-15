package com.microservice.order.controller;

import org.springframework.web.bind.annotation.RestController;

import com.microservice.order.DTO.Order;
import com.microservice.order.DTO.OrderRequestDTO;
import com.microservice.order.DTO.OrderRequestUpdateDTO;
import com.microservice.order.DTO.OrderResponseDTO;
import com.microservice.order.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@RestController("order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<OrderResponseDTO> create(@RequestBody OrderRequestDTO requestBody) {
        OrderResponseDTO orderResponse = orderService.create(requestBody);
        return ResponseEntity.ok().body(orderResponse);
    }

    @PostMapping("/update")
    public ResponseEntity<OrderResponseDTO> update(@RequestBody OrderRequestUpdateDTO requestBody) {
        OrderResponseDTO orderResponse = orderService.update(requestBody);
        return ResponseEntity.ok().body(orderResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orders = orderService.getAll();
        return ResponseEntity.ok().body(orders);
    }
}
