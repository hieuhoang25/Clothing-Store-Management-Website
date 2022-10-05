package com.hicode.cozastore.service;

import com.hicode.cozastore.collection.Tag;

import java.util.List;

public interface TagService {
    Tag insert(Tag tag);
    Tag update(Tag tag);
    List<Tag> findAll();
    Tag findById(String id);
    Tag findByName(String name);
    void delete(Tag tag);
}
