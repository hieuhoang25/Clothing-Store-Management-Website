package com.hicode.cozastore.repository;

import com.hicode.cozastore.collection.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role,String> {

    Optional<Role> findRoleByName(String name);
}
