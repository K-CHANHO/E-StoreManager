package com.shinewide.estoremanager.repository;

import com.shinewide.estoremanager.domain.Order;
import com.shinewide.estoremanager.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
