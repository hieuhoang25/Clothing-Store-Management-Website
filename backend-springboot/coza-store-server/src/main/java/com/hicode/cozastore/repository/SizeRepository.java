package com.hicode.cozastore.repository;

import com.hicode.cozastore.collection.Size;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SizeRepository extends MongoRepository<Size,String> {
    Optional<Size> findByName(String name);
}
