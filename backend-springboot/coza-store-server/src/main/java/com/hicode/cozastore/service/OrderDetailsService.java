package com.hicode.cozastore.service;

import com.hicode.cozastore.collection.OrderDetails;

import java.util.List;

public interface OrderDetailsService {
    OrderDetails insert(OrderDetails orderDetails);
    OrderDetails update(OrderDetails orderDetails);
    List<OrderDetails> findAll();
    OrderDetails findById(String id);
    void delete(OrderDetails orderDetails);
}
