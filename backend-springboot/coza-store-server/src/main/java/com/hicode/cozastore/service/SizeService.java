package com.hicode.cozastore.service;

import com.hicode.cozastore.collection.Size;

import java.util.List;

public interface SizeService {
    Size insert(Size size);
    Size update(Size size);
    List<Size> findAll();
    Size findById(String id);
    Size findByName(String name);
    void delete(Size size);
}
