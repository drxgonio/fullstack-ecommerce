package com.example.ecm.dao;

import com.example.ecm.model.entity.Product;
import com.example.ecm.model.entity.ProductCategory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    Optional<Product> findByUrl(String url);

    List<Product> findAllByProductCategory(Pageable pageable, ProductCategory productCategory);

    List<Product> findTop8ByOrderByDateCreatedDesc();

    List<Product> findAllByNameContainingIgnoreCase(String name, Pageable pageable);

    List<Product> findTop8ByProductCategoryAndIdIsNot(ProductCategory productCategory, Long id);

    List<Product> findAllByProductCategoryIsNot(ProductCategory productCategory, Pageable pageable);
}
