package com.microservice.payment.DTO;

import com.microservice.payment.utils.PaymentStatus;

public class PaymentResponseDTO {
    private String userId;
    private String orderId;
    private PaymentStatus status; // <- payment_approved | payment_rejected

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }
}