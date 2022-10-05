package com.hicode.cozastore.service.impl;

import com.hicode.cozastore.collection.Product;
import com.hicode.cozastore.exception.ApiExceptionHandler;
import com.hicode.cozastore.repository.ProductRepostory;
import com.hicode.cozastore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepostory productRepostory;

    @Override
    public Product insert(Product product) {
            productRepostory.insert(product);
        return product;
    }

    @Override
    public Product update(Product product) {
        productRepostory.save(product);
        return product;
    }

    @Override
    public List<Product> findAll() {
        return productRepostory.findAll();
    }

    @Override
    public Product findById(String id) {
        return productRepostory.findById(id).orElse(null);
    }

    @Override
    public Product findByName(String name) {
        return productRepostory.findByName(name).orElse(null);
    }

    @Override
    public void delete(Product product) {
          productRepostory.delete(product);
    }
}
