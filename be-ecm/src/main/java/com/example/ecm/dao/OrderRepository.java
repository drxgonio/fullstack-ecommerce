package com.example.ecm.dao;

import com.example.ecm.model.entity.Order;
import com.example.ecm.model.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserOrderByDateDesc(User user, Pageable pageable);

    Optional<Integer> countAllByUser(User user);
}
