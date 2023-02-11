package com.example.ecm.service.cache;

import com.example.ecm.model.entity.Product;
import com.example.ecm.model.entity.ProductCategory;

import java.util.List;

public interface ProductCacheService {

    Product findByUrl(String url);

    List<Product> findTop8ByOrderByDateCreatedDesc();

    List<Product> getRelatedProducts(ProductCategory productCategory, Long id);

}
