package com.microservice.payment.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.payment.DTO.UserAccount;
import com.microservice.payment.DTO.PaymentRequestDTO;
import com.microservice.payment.DTO.PaymentResponseDTO;
import com.microservice.payment.repository.UserAccountRepository;
import com.microservice.payment.utils.PaymentStatus;

@Service
public class paymentService {
    @Autowired
    UserAccountRepository userAccountRepository;

    public PaymentResponseDTO debit(PaymentRequestDTO request) {
        Optional<UserAccount> optionalUserAcount = userAccountRepository.findById(request.getUserId());
        PaymentResponseDTO paymentResponse = new PaymentResponseDTO();
        paymentResponse.setOrderId(request.getOrderId());
        paymentResponse.setUserId(request.getUserId());
        paymentResponse.setStatus(PaymentStatus.PAYMENT_REJECTED);

        if (!optionalUserAcount.isPresent()) {
            return paymentResponse;
        }

        UserAccount userAccount = optionalUserAcount.get();

        if (userAccount.getBalance() >= request.getPrice()) {
            userAccount.setBalance(userAccount.getBalance() - request.getPrice());
            userAccountRepository.save(userAccount);
            paymentResponse.setStatus(PaymentStatus.PAYMENT_APPROVED);
        }

        return paymentResponse;
    }

    public void credit(PaymentRequestDTO request) {
        Optional<UserAccount> optionalUserAcount = userAccountRepository.findById(request.getUserId());
        if (optionalUserAcount.isPresent()) {
            UserAccount userAccount = optionalUserAcount.get();
            userAccount.setBalance(userAccount.getBalance() + request.getPrice());
            userAccountRepository.save(userAccount);
        }
    }
}
