package com.shinewide.estoremanager.coupang;

import com.coupang.openapi.sdk.Hmac;
import com.shinewide.estoremanager.coupang.dto.ProductDto;
import com.shinewide.estoremanager.coupang.dto.ProductListDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;

@Service
@Slf4j
public class CoupangService {

    @Value("${coupang.accesskey}") private String ACCESS_KEY;
    @Value("${coupang.secretkey}") private String SECRET_KEY;

    private String authorization;
    private RestClient restClient;


    public CoupangService() {
        log.info("CoupangService Constructor Executed");

        //authorization 발급
        String method = "GET";
        String path = "/v2/providers/openapi/apis/api/v4/vendors/A00******/returnRequests";

        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromPath(path)
                .queryParam("createdAtFrom", "2025-01-07")
                .queryParam("createdAtTo", "2025-01-14")
                .queryParam("status", "UC");

        log.info("accesskey : {}", ACCESS_KEY);
        log.info("secretkey : {}", SECRET_KEY);
        this.authorization = Hmac.generate(method, uriBuilder.build().toString(), SECRET_KEY, ACCESS_KEY);

        this.restClient = RestClient.builder()
                .baseUrl("api-gateway.coupang.com")
                .defaultHeader("Authorization", authorization)
                .build();

    }

    public ProductListDto getProductList(){

        RestClient restClient = RestClient.builder()
                .baseUrl("https://api-gateway.coupang.com")
                .build();

        ProductListDto result = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v2/providers/seller_api/apis/api/v1/marketplace/seller-products")
                        .queryParam("vendorId", "")
                        .build())
                .retrieve()
                .body(ProductListDto.class);

        return result;
    }
}
