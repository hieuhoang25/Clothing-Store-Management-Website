package com.hicode.cozastore.repository;

import com.hicode.cozastore.collection.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order,String> {
}
