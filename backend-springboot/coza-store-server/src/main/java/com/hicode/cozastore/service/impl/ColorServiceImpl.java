package com.hicode.cozastore.service.impl;

import com.hicode.cozastore.collection.Color;
import com.hicode.cozastore.repository.ColorRepository;
import com.hicode.cozastore.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {
    private final ColorRepository colorRepository;
    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public Color insert(Color color) {
        colorRepository.insert(color);
        return color;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public Color update(Color color) {
        colorRepository.save(color);
        return color;
    }

    @Override
    public List<Color> findAll() {
        return colorRepository.findAll();
    }

    @Override
    public Color findById(String id) {
        return colorRepository.findById(id).orElse(null);
    }

    @Override
    public Color findByName(String name) {
        return colorRepository.findColorByName(name).orElse(null);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public void delete(Color color) {
        colorRepository.delete(color);
    }
}
