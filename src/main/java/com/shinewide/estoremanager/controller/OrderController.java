package com.shinewide.estoremanager.controller;

import com.shinewide.estoremanager.domain.Order;
import com.shinewide.estoremanager.dto.OrderItemDto;
import com.shinewide.estoremanager.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/{userId}")
    public ResponseEntity<Order> createOrder(@PathVariable Long userId, @RequestBody List<OrderItemDto> items){
        Order order = orderService.createOrder(userId, items);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Order>> getUserOrders(@PathVariable Long userId){
        List<Order> userOrders = orderService.getUserOrders(userId);
        return ResponseEntity.ok(userOrders);
    }

}
