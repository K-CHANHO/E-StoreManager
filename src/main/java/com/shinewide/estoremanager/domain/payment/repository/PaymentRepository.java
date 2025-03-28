package com.shinewide.estoremanager.domain.payment.repository;

import com.shinewide.estoremanager.domain.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
