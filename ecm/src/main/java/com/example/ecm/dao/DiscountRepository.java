package com.example.ecm.dao;

import com.example.ecm.model.entity.Discount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountRepository extends CrudRepository<Discount, Long> {
    Optional<Discount> findByCode(String code);
}
