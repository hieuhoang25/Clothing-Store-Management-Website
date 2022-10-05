package com.hicode.cozastore.repository;

import com.hicode.cozastore.collection.Favorite;
import com.hicode.cozastore.collection.Product;
import com.hicode.cozastore.collection.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FavoriteRepostory extends MongoRepository<Favorite,String> {

    Optional<Favorite> findByUser(User user);
    Optional<Favorite> findByProduct(Product product);

}
