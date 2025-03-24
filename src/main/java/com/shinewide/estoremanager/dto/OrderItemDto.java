package com.shinewide.estoremanager.dto;

import lombok.Data;

@Data
public class OrderItemDto {
    private Long productId;
    private int quantity;
}
