package com.shinewide.estoremanager.coupang;

import com.shinewide.estoremanager.coupang.dto.ProductListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CoupangController {

    private final CoupangService coupangService;

    @GetMapping("/test")
    public ResponseEntity gettest(){

        ProductListDto productList = coupangService.getProductList();

        return new ResponseEntity(productList, HttpStatus.OK);
    }

}
