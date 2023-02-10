package com.example.ecm.model.request.discount;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class ApplyDiscountRequest {

    @NotBlank
    private String code;
}
