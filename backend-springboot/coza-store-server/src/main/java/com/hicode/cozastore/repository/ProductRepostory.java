package com.hicode.cozastore.repository;

import com.hicode.cozastore.collection.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProductRepostory extends MongoRepository<Product,String> {
    Optional<Product> findByName(String name);
}
