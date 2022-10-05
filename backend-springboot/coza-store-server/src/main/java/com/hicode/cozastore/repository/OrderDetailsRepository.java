package com.hicode.cozastore.repository;

import com.hicode.cozastore.collection.OrderDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderDetailsRepository extends MongoRepository<OrderDetails,String> {
}
