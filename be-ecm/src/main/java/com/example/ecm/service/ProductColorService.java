package com.example.ecm.service;

import com.example.ecm.model.response.color.ProductColorResponse;

import java.util.List;

public interface ProductColorService {
    List<ProductColorResponse> findAll();
}
