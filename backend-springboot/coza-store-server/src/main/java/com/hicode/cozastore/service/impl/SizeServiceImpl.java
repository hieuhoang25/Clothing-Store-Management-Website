package com.hicode.cozastore.service.impl;

import com.hicode.cozastore.collection.Size;
import com.hicode.cozastore.repository.SizeRepository;
import com.hicode.cozastore.service.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SizeServiceImpl implements SizeService {
    private final SizeRepository sizeRepository;
    @Override
    @Transactional(rollbackFor = {Exception.class,Throwable.class})
    public Size insert(Size size) {
        sizeRepository.insert(size);
        return size;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,Throwable.class})
    public Size update(Size size) {
        sizeRepository.save(size);
        return size;
    }

    @Override
    public List<Size> findAll() {
        return sizeRepository.findAll();
    }

    @Override
    public Size findById(String id) {
        return sizeRepository.findById(id).orElse(null);
    }

    @Override
    public Size findByName(String name) {
        return sizeRepository.findByName(name).orElse(null);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class,Throwable.class})
    public void delete(Size size) {
        sizeRepository.delete(size);
    }
}
