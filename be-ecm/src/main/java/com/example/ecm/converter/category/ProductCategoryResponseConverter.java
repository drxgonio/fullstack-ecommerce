package com.example.ecm.converter.category;

import com.example.ecm.model.dto.CategoryDTO;
import com.example.ecm.model.entity.ProductCategory;
import com.example.ecm.model.response.category.ProductCategoryResponse;
import org.springframework.stereotype.Component;

import java.util.function.Function;


@Component
public class ProductCategoryResponseConverter implements Function<ProductCategory, ProductCategoryResponse> {
    @Override
    public ProductCategoryResponse apply(ProductCategory productCategory) {
        ProductCategoryResponse productCategoryResponse = new ProductCategoryResponse();
        productCategoryResponse.setCategory(CategoryDTO.builder().name(productCategory.getName()).build());
        return productCategoryResponse;
    }
}
