package com.hicode.cozastore.repository;

import com.hicode.cozastore.collection.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CategoryRepository extends MongoRepository<Category,String> {
    Optional<Category> findByName(String name);
}
