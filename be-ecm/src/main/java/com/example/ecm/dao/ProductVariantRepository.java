package com.example.ecm.dao;

import com.example.ecm.model.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long>,
        JpaSpecificationExecutor<ProductVariant> {
    List<ProductVariant> findTop8ByOrderBySellCountDesc();
}
