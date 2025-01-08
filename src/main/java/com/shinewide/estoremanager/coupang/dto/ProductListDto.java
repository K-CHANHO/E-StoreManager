package com.shinewide.estoremanager.coupang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductListDto {

    private Number code;
    private String message;
    private String nextToken;
    private ArrayList<ProductDto> data;
}
