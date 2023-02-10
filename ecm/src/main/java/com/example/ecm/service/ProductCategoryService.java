package com.example.ecm.service;

import com.example.ecm.model.response.category.ProductCategoryResponse;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategoryResponse> findAllByOrderByName();
}
