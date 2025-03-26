package com.shinewide.estoremanager.domain.order.repository;

import com.shinewide.estoremanager.domain.order.entity.Order;
import com.shinewide.estoremanager.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
