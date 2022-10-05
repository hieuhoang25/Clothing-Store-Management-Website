package com.hicode.cozastore.service;

import com.hicode.cozastore.collection.Role;

import java.util.List;

public interface RoleService {
    Role insert(Role r);
    Role update(Role r);
    List<Role> findAll();
    Role findById(String id);
    Role findByName(String name);
    void delete(Role role);

}
