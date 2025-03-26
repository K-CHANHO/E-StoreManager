package com.shinewide.estoremanager.domain.order.service;

import com.shinewide.estoremanager.domain.order.dto.OrderItemDto;
import com.shinewide.estoremanager.domain.order.entity.Order;
import com.shinewide.estoremanager.domain.order.entity.OrderItem;
import com.shinewide.estoremanager.domain.order.repository.OrderRepository;
import com.shinewide.estoremanager.domain.product.entity.Product;
import com.shinewide.estoremanager.domain.user.entity.User;
import com.shinewide.estoremanager.domain.product.repository.ProductRepository;
import com.shinewide.estoremanager.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Transactional
    public Order createOrder(Long userId, List<OrderItemDto> items){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order();
        order.setUser(user);

        for(OrderItemDto itemDto : items){
            Product product = productRepository.findById(itemDto.getProductId())
                    .orElseThrow(() -> new RuntimeException("product not found"));

            if(product.getStock() < itemDto.getQuantity()){
                throw new RuntimeException("Not enough stock for product: " + product.getName());
            }

            // 재고감소
            product.setStock(product.getStock() - itemDto.getQuantity());
            productRepository.save(product);

            // 주문 항목 추가
            OrderItem orderItem = new OrderItem(product, itemDto.getQuantity());
            order.addOrderItem(orderItem);
        }

        return orderRepository.save(order);
    }

    public List<Order> getUserOrders(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<Order> orderList = orderRepository.findByUser(user);
        return orderList;
    }

}
