package com.hicode.cozastore.service;

import com.hicode.cozastore.collection.Product;

import java.util.List;

public interface ProductService {
    Product insert(Product product);
    Product update(Product product);
    List<Product> findAll();
    Product findById(String id);
    Product findByName(String name);
    void delete(Product product);

}
