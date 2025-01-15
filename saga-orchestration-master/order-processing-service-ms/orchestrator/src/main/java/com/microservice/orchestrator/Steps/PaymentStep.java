package com.microservice.orchestrator.Steps;

import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.microservice.orchestrator.DTO.PaymentRequestDTO;
import com.microservice.orchestrator.DTO.PaymentResponseDTO;

import reactor.core.publisher.Mono;

public class PaymentStep implements Step<PaymentRequestDTO, PaymentResponseDTO> {
    private final WebClient paymentWebClient = WebClient
            .builder()
            .baseUrl("http://localhost:8081")
            .build();

    @Override
    public Mono<PaymentResponseDTO> process(PaymentRequestDTO request) {
        Mono<PaymentResponseDTO> paymentResponse = this.paymentWebClient
                .post()
                .uri("/debit")
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(PaymentResponseDTO.class);

        return paymentResponse;
    }

    @Override
    public Mono<PaymentResponseDTO> rollback(PaymentRequestDTO request) {
        Mono<PaymentResponseDTO> paymentResponse = this.paymentWebClient
                .post()
                .uri("/credit")
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(PaymentResponseDTO.class);

        return paymentResponse;
    }

}
