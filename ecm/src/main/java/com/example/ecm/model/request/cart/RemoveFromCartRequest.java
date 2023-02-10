package com.example.ecm.model.request.cart;

import lombok.Data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Data
public class RemoveFromCartRequest {

    @NotNull
    @Min(1)
    private Long cartItemId;
}
