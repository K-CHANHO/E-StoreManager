package com.shinewide.estoremanager.domain.order.entity;

import com.shinewide.estoremanager.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // 주문한 사용자

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문 상태

    private LocalDateTime orderDate;

    public Order(){
        this.orderDate = LocalDateTime.now();
        this.status = OrderStatus.PENDING;
    }

    public void setUser(User user){
        this.user = user;
    }

    public void addOrderItem(OrderItem item){
        orderItems.add(item);
        item.setOrder(this);
    }

    public void completeOrder(){
        this.status = OrderStatus.COMPLETED;
    }

    public enum OrderStatus {
        PENDING,    // 결제대기
        COMPLETED,  // 결제완료
        CANCELED    // 주문취소
    }
}
