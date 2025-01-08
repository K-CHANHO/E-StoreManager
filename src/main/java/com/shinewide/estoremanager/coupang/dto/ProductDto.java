package com.shinewide.estoremanager.coupang.dto;

import lombok.Data;

@Data
public class ProductDto {

    private String sellerProductId;
    private String sellerProductName;
    private Number displayCategoryCode;
    private Number categoryId;
    private Number productId;
    private String vendorId;
    private String saleStartedAt; // "yyyy-MM-ddTHH:mm:ss" 형식
    private String saleEndedAt; // "yyyy-MM-ddTHH:mm:ss" 형식
    private String brand;
    private String statusName; // IN_REVIEW 심사중, SAVED 임시저장, APPROVING 승인대기중, APPROVED 승인완료, PARTIAL_APPROVED 부분승인완료, DENIED 승인반려, DELETED 상품삭제
    private String createdAt; // "yyyy-MM-ddTHH:mm:ss" 형식

}
