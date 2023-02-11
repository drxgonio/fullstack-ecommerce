package com.example.ecm.service.cache;


import com.example.ecm.model.entity.ProductVariant;

import java.util.List;

public interface ProductVariantCacheService {

    ProductVariant findById(Long id);

    List<ProductVariant> findTop8ByOrderBySellCountDesc();
}
