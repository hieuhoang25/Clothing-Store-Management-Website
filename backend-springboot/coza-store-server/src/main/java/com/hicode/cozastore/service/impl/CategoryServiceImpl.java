package com.hicode.cozastore.service.impl;

import com.hicode.cozastore.collection.Category;
import com.hicode.cozastore.repository.CategoryRepository;
import com.hicode.cozastore.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public Category insert(Category category) {
        categoryRepository.insert(category);
        return category;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public Category update(Category category) {
        categoryRepository.save(category);
        return category;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(String id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category findByName(String name) {
        return categoryRepository.findByName(name).orElse(null);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public void delete(Category category) {
        categoryRepository.delete(category);
    }
}
