package com.shinewide.estoremanager.dto;

import com.shinewide.estoremanager.domain.Product;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

import static com.shinewide.estoremanager.domain.Product.*;

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
