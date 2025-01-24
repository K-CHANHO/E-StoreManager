package com.shinewide.estoremanager.coupang;

import com.coupang.openapi.sdk.Hmac;
import com.shinewide.estoremanager.coupang.dto.ProductListDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Slf4j
public class CoupangService {

    private String ACCESS_KEY;
    private String SECRET_KEY;

    private String authorization;
    private RestClient restClient;

    public CoupangService(@Value("${coupang.accesskey}") String accessKey, @Value("${coupang.secretkey}") String secretKey) {
        ACCESS_KEY = accessKey;
        SECRET_KEY = secretKey;

        log.info("CoupangService Constructor Executed");

        //authorization 발급
        String method = "GET";
        String path = "/v2/providers/openapi/apis/api/v4/vendors/A01192308/returnRequests";

        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromPath(path)
                .queryParam("createdAtFrom", "2025-01-20")
                .queryParam("createdAtTo", "2025-01-28")
                .queryParam("status", "UC");

        log.info("accesskey : {}", ACCESS_KEY);
        log.info("secretkey : {}", SECRET_KEY);
        authorization = Hmac.generate(method, uriBuilder.build().toString(), SECRET_KEY, ACCESS_KEY);
        log.info("authorization : {}", authorization);

        this.restClient = RestClient.builder()
                .baseUrl("https://api-gateway.coupang.com")
                .defaultHeader("Authorization", authorization)
                .defaultHeader("X-Reqeusted-By", "A01192308")
                .build();

        log.info("{}", restClient.toString());
        log.info("CoupangService Constructor Done");

    }

    public void getProperties(){
        log.info("ACCESS_KEY : {}", ACCESS_KEY);
        log.info("SECRET_KEY : {}", SECRET_KEY);
    }

    public ProductListDto getProductList(){

        log.info("getPoductList Start");
        log.info("{}", restClient.toString());

        ProductListDto result = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v2/providers/seller_api/apis/api/v1/marketplace/seller-products")
                        .queryParam("vendorId", "A01192308")
                        .build())
                .retrieve()
                .body(ProductListDto.class);

        return result;
    }
}
