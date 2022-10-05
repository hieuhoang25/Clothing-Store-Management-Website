package com.hicode.cozastore.service;

import com.hicode.cozastore.collection.Order;

import java.util.List;

public interface OrderService {
    Order insert(Order order);
    Order update(Order order);
    List<Order> findAll();
    Order findById(String id);
    void delete(Order order);
}
