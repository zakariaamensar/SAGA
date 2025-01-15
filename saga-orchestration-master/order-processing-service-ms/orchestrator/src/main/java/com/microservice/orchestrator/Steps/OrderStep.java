package com.microservice.orchestrator.Steps;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.microservice.orchestrator.DTO.OrderRequestDTO;
import com.microservice.orchestrator.DTO.OrderRequestUpdateDTO;
import com.microservice.orchestrator.DTO.OrderResponseDTO;

import reactor.core.publisher.Mono;

public class OrderStep implements Step<OrderRequestDTO, OrderResponseDTO> {
    private final WebClient orderWebClient = WebClient
            .builder()
            .baseUrl("http://localhost:8082")
            .build();

    @Override
    public Mono<OrderResponseDTO> process(OrderRequestDTO request) {
        return this.orderWebClient
                .post()
                .uri("/create")
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(OrderResponseDTO.class);
    }

    @Override
    public Mono<OrderResponseDTO> rollback(OrderRequestDTO request) {
        return this.orderWebClient
                .post()
                .uri("/update")
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(OrderResponseDTO.class);
    }

    public Mono<OrderResponseDTO> update(OrderRequestUpdateDTO request) {
        return this.orderWebClient
                .post()
                .uri("/update")
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(OrderResponseDTO.class);
    }
}
