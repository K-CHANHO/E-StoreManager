package com.shinewide.estoremanager.domain.product.entity;

import com.shinewide.estoremanager.domain.product.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // 상품명
    private String description; // 상품 설명
    private BigDecimal price; // 가격
    private int stock; // 재고 수량

    private String imageUrl; // 이미지

    @Enumerated(EnumType.STRING)
    private Category category; // 상품 카테고리
}
