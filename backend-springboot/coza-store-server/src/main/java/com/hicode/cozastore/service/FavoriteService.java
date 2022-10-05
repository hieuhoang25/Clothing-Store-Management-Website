package com.hicode.cozastore.service;

import com.hicode.cozastore.collection.Favorite;
import com.hicode.cozastore.collection.Product;
import com.hicode.cozastore.collection.User;

import java.util.List;

public interface FavoriteService {
    Favorite insert(Favorite favorite);
    Favorite update(Favorite favorite);
    List<Favorite> findAll();
    Favorite findById(String id);
    Favorite findByUser(User user);
    Favorite findByProduct(Product product);
    void delete(Favorite favorite);
}
