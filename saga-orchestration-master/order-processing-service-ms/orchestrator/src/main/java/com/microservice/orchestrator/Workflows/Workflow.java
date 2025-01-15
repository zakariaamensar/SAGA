package com.microservice.orchestrator.Workflows;

import com.microservice.orchestrator.DTO.InventoryRequestDTO;
import com.microservice.orchestrator.DTO.InventoryResponseDTO;
import com.microservice.orchestrator.DTO.OrchestratorResponseDTO;
import com.microservice.orchestrator.DTO.OrderRequestDTO;
import com.microservice.orchestrator.DTO.OrderRequestUpdateDTO;
import com.microservice.orchestrator.DTO.OrderResponseDTO;
import com.microservice.orchestrator.DTO.PaymentRequestDTO;
import com.microservice.orchestrator.DTO.PaymentResponseDTO;
import com.microservice.orchestrator.Steps.InventoryStep;
import com.microservice.orchestrator.Steps.OrderStep;
import com.microservice.orchestrator.Steps.PaymentStep;
import com.microservice.orchestrator.utils.InventoryStatus;
import com.microservice.orchestrator.utils.OrderStatus;
import com.microservice.orchestrator.utils.PaymentStatus;

import reactor.core.publisher.Mono;

public class Workflow {
    OrderStep orderStep = new OrderStep();
    InventoryStep inventoryStep = new InventoryStep();
    PaymentStep paymentStep = new PaymentStep();

    public OrchestratorResponseDTO start(OrderRequestDTO requestDTO) {
        Mono<OrderResponseDTO> orderResponse = this.orderStep.process(requestDTO);
        orderResponse.subscribe(order -> {
            if (order.getStatus().equals(OrderStatus.ORDER_CREATED)) {
                InventoryRequestDTO inventoryRequest = new InventoryRequestDTO();
                inventoryRequest.setOrderId(order.getOrderId());
                inventoryRequest.setProductId(order.getProductId());
                inventoryRequest.setUserId(order.getUserId());

                Mono<InventoryResponseDTO> inventoryResponse = this.inventoryStep
                        .process(inventoryRequest);

                inventoryResponse.subscribe(inventory -> {
                    if (inventory.getStatus().equals(InventoryStatus.AVAILABLE)) {
                        PaymentRequestDTO paymentRequest = new PaymentRequestDTO();
                        paymentRequest.setOrderId(inventory.getOrderId());
                        paymentRequest.setPrice(inventory.getPrice());
                        paymentRequest.setUserId(inventory.getUserId());
                        Mono<PaymentResponseDTO> paymentResponse = this.paymentStep.process(paymentRequest);
                        paymentResponse.subscribe(payment -> {
                            if (payment.getStatus().equals(PaymentStatus.PAYMENT_APPROVED)) {
                                OrderRequestUpdateDTO orderRequestUpdate = new OrderRequestUpdateDTO();
                                orderRequestUpdate.setOrderId(payment.getOrderId());
                                orderRequestUpdate.setOrderStatus(OrderStatus.ORDER_COMPLETED);
                                Mono<OrderResponseDTO> orderResponseMono = this.orderStep.update(orderRequestUpdate);
                                orderResponseMono.subscribe();
                            }
                        });
                    }
                });
            }
        });

        return new OrchestratorResponseDTO();
    }
}