package com.hicode.cozastore.service;

import com.hicode.cozastore.collection.Color;

import java.util.List;

public interface ColorService {
    Color insert(Color color);
    Color update(Color color);
    List<Color> findAll();
    Color findById(String id);
    Color findByName(String name);
    void delete(Color color);
}
