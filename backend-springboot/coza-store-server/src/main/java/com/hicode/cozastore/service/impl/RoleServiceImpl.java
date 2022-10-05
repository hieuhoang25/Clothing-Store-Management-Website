package com.hicode.cozastore.service.impl;

import com.hicode.cozastore.collection.Role;
import com.hicode.cozastore.repository.RoleRepository;
import com.hicode.cozastore.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Override
    @Transactional(rollbackFor ={ Exception.class, Throwable.class})
    public Role insert(Role r) {
        roleRepository.insert(r);
        return r;
    }

    @Override
    @Transactional(rollbackFor ={ Exception.class, Throwable.class})
    public Role update(Role r) {
        roleRepository.save(r);
        return r;
    }

    @Override
    public List<Role> findAll() {

        return roleRepository.findAll();
    }

    @Override
    public Role findById(String id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findRoleByName(name).orElse(null);
    }

    @Override
    @Transactional(rollbackFor ={ Exception.class, Throwable.class})
    public void delete(Role role) {
        roleRepository.delete(role);
    }
}
