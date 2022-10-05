package com.hicode.cozastore.service.impl;

import com.hicode.cozastore.collection.OrderDetails;
import com.hicode.cozastore.repository.OrderDetailsRepository;
import com.hicode.cozastore.service.OrderDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailsServiceImpl implements OrderDetailsService {
    private final OrderDetailsRepository orderDetailsRepository;
    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public OrderDetails insert(OrderDetails orderDetails) {
        orderDetailsRepository.insert(orderDetails);
        return orderDetails;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public OrderDetails update(OrderDetails orderDetails) {
        orderDetailsRepository.save(orderDetails);
        return orderDetails;
    }

    @Override
    public List<OrderDetails> findAll() {
        return orderDetailsRepository.findAll();
    }

    @Override
    public OrderDetails findById(String id) {
        return orderDetailsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found by id"));
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public void delete(OrderDetails orderDetails) {
        orderDetailsRepository.delete(orderDetails);

    }
}
