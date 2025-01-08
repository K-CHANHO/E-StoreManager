package com.shinewide.estoremanager.coupang;

import com.shinewide.estoremanager.coupang.dto.ProductListDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class CoupangServiceTest {

    @Autowired CoupangService coupangService;

    @Test
    void getProductList() {
        ProductListDto productList = coupangService.getProductList();
        log.info("테스트 결과");
        log.info(productList.toString());
    }
}