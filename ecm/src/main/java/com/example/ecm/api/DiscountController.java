package com.example.ecm.api;

import com.example.ecm.model.request.discount.ApplyDiscountRequest;
import com.example.ecm.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DiscountController extends ApiController {

    private final DiscountService discountService;

    @Autowired
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @PostMapping(value = "/cart/discount")
    public ResponseEntity<HttpStatus> applyDiscount(@RequestBody @Validated ApplyDiscountRequest applyDiscountRequest) {
        discountService.applyDiscount(applyDiscountRequest.getCode());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
