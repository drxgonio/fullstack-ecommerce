package com.example.ecm.service;

import com.example.ecm.model.request.order.PostOrderRequest;
import com.example.ecm.model.response.order.OrderResponse;

import java.util.List;

public interface OrderService {
    Integer getAllOrdersCount();

    List<OrderResponse> getAllOrders(Integer page, Integer pageSize);

    OrderResponse postOrder(PostOrderRequest postOrderRequest);
}
