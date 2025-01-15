package com.microservice.orchestrator.controller;

import org.springframework.web.bind.annotation.RestController;

import com.microservice.orchestrator.DTO.OrchestratorResponseDTO;
import com.microservice.orchestrator.DTO.OrderRequestDTO;
import com.microservice.orchestrator.Workflows.Workflow;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController("order-processing")
public class OrderProcessingController {
    @PostMapping("/orders")
    public OrchestratorResponseDTO processOrder(@RequestBody OrderRequestDTO request) {
        Workflow workflow = new Workflow();
        return workflow.start(request);
    }
}
