package com.microservice.orchestrator.Steps;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.microservice.orchestrator.DTO.InventoryRequestDTO;
import com.microservice.orchestrator.DTO.InventoryResponseDTO;

import reactor.core.publisher.Mono;

public class InventoryStep implements Step<InventoryRequestDTO, InventoryResponseDTO> {
    private final WebClient paymentWebClient = WebClient
            .builder()
            .baseUrl("http://localhost:8080")
            .build();

    @Override
    public Mono<InventoryResponseDTO> process(InventoryRequestDTO request) {
        Mono<InventoryResponseDTO> paymentResponse = this.paymentWebClient
                .post()
                .uri("/deduct")
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(InventoryResponseDTO.class);

        return paymentResponse;
    }

    @Override
    public Mono<InventoryResponseDTO> rollback(InventoryRequestDTO request) {
        Mono<InventoryResponseDTO> paymentResponse = this.paymentWebClient
                .post()
                .uri("/add")
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(InventoryResponseDTO.class);

        return paymentResponse;
    }

}
