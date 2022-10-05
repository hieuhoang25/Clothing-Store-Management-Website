package com.hicode.cozastore.service;


import com.hicode.cozastore.collection.User;

import java.util.List;

public interface UserService {
    User insert(User u);
    User update(User u);
    List<User> findAll();
    User findById(String id);
    User findByUsername(String username);
    void delete(User user);
}
