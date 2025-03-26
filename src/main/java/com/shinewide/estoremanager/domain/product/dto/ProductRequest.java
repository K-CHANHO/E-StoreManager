package com.shinewide.estoremanager.domain.product.dto;

import com.shinewide.estoremanager.domain.product.Category;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequest {

    private String name;
    private String description;
    private BigDecimal price;
    private int stock;
    private String imageUrl;
    private Category category;

}
