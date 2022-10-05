package com.hicode.cozastore.service;

import com.hicode.cozastore.collection.Category;

import java.util.List;

public interface CategoryService {
    Category insert(Category category);
    Category update(Category category);
    List<Category> findAll();
    Category findById(String id);
    Category findByName(String name);
    void delete(Category category);
}
