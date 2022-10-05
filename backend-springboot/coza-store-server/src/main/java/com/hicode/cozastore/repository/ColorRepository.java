package com.hicode.cozastore.repository;

import com.hicode.cozastore.collection.Color;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ColorRepository extends MongoRepository<Color,String> {
    Optional<Color> findColorByName(String name);
}
