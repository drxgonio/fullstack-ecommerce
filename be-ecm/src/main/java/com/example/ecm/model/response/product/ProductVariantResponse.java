package com.example.ecm.model.response.product;

import com.example.ecm.model.dto.ProductVariantDTO;
import lombok.Data;


@Data
public class ProductVariantResponse {
    private Long id;
    private String name;
    private String url;
    private ProductVariantDTO productVariant;
}
