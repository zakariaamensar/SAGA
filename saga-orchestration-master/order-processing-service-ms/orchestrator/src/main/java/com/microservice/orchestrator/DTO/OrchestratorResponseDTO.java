package com.microservice.orchestrator.DTO;

import com.microservice.orchestrator.utils.OrderStatus;

public class OrchestratorResponseDTO {
    private String orderId;
    private OrderStatus status;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
