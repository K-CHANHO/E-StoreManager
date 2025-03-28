package com.shinewide.estoremanager.domain.payment.service;

import com.shinewide.estoremanager.domain.order.entity.Order;
import com.shinewide.estoremanager.domain.order.entity.OrderStatus;
import com.shinewide.estoremanager.domain.order.repository.OrderRepository;
import com.shinewide.estoremanager.domain.payment.entity.Payment;
import com.shinewide.estoremanager.domain.payment.entity.PaymentStatus;
import com.shinewide.estoremanager.domain.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public Payment processPayment(Long orderId, BigDecimal amount, String paymentMethod){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        // TODO : 실제 PG API 추가

        // 결제 정보 생성
        Payment payment = Payment.builder()
                .order(order)
                .status(PaymentStatus.COMPLETED)
                .amount(amount)
                .paymentDate(LocalDateTime.now())
                .paymentMethod(paymentMethod)
                .build();

        // 결제 정보 저장
        Payment savedPayment = paymentRepository.save(payment);

        // 주문 상태 변경 (PENDING -> COMPLETED)
        order.setStatus(OrderStatus.COMPLETED);
        orderRepository.save(order);

        return savedPayment;
    }
}
