package com.hicode.cozastore.service.impl;

import com.hicode.cozastore.collection.Favorite;
import com.hicode.cozastore.collection.Product;
import com.hicode.cozastore.collection.User;
import com.hicode.cozastore.repository.FavoriteRepostory;
import com.hicode.cozastore.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteRepostory favoriteRepostory;

    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    @Override
    public Favorite insert(Favorite favorite) {
        favoriteRepostory.insert(favorite);
        return favorite;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public Favorite update(Favorite favorite) {
        favoriteRepostory.save(favorite);
        return favorite;
    }

    @Override
    public List<Favorite> findAll() {
        return favoriteRepostory.findAll();
    }

    @Override
    public Favorite findById(String id) {
        return favoriteRepostory.findById(id).orElseThrow(() -> new IllegalArgumentException("Favorite not found bt id"));
    }

    @Override
    public Favorite findByUser(User user) {
        return favoriteRepostory.findByUser(user).orElseThrow(() -> new IllegalArgumentException("Favorite not found by user"));
    }

    @Override
    public Favorite findByProduct(Product product) {
        return favoriteRepostory.findByProduct(product).orElseThrow(() -> new IllegalArgumentException("Favorite not founr by product"));
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public void delete(Favorite favorite) {
        favoriteRepostory.delete(favorite);
    }
}
