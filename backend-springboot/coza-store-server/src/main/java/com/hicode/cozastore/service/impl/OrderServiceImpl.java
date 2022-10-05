package com.hicode.cozastore.service.impl;

import com.hicode.cozastore.collection.Order;
import com.hicode.cozastore.repository.OrderRepository;
import com.hicode.cozastore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public Order insert(Order order) {
        orderRepository.insert(order);
        return order;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public Order update(Order order) {
        orderRepository.save(order);
        return order;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(String id) {
        return orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found by id"));
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public void delete(Order order) {
        orderRepository.delete(order);
    }
}
