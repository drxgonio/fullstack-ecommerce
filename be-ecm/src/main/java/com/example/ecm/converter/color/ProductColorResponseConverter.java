package com.example.ecm.converter.color;

import com.example.ecm.model.dto.ColorDTO;
import com.example.ecm.model.entity.Color;
import com.example.ecm.model.response.color.ProductColorResponse;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProductColorResponseConverter implements Function<Color, ProductColorResponse> {
    @Override
    public ProductColorResponse apply(Color color) {
        ProductColorResponse productColorResponse = new ProductColorResponse();
        productColorResponse.setColor(ColorDTO.builder().name(color.getName()).hex(color.getHex()).build());
        return productColorResponse;
    }
}
