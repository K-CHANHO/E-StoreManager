package com.shinewide.estoremanager.domain.payment.controller;

import com.shinewide.estoremanager.domain.payment.dto.PaymentRequest;
import com.shinewide.estoremanager.domain.payment.entity.Payment;
import com.shinewide.estoremanager.domain.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/{orderId}")
    public ResponseEntity<Payment> processPayment(@PathVariable Long orderId, @RequestBody PaymentRequest request){

        Payment payment = paymentService.processPayment(orderId, request.getAmount(), request.getPaymentMethod());
        return ResponseEntity.ok(payment);

    }
}
