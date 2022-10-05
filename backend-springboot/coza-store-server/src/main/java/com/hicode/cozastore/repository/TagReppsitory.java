package com.hicode.cozastore.repository;

import com.hicode.cozastore.collection.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TagReppsitory extends MongoRepository<Tag,String> {

    Optional<Tag> findByName(String name);
}
