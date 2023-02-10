package com.example.ecm.service.cache;

import com.example.ecm.model.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryCacheService {
    List<ProductCategory> findAllByOrderByName();
}
