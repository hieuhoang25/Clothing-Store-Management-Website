package com.hicode.cozastore.service.impl;

import com.hicode.cozastore.collection.Tag;
import com.hicode.cozastore.exception.ApiExceptionHandler;
import com.hicode.cozastore.repository.TagReppsitory;
import com.hicode.cozastore.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagReppsitory tagReppsitory;

    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public Tag insert(Tag tag) {
        tagReppsitory.insert(tag);
        return tag;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public Tag update(Tag tag) {
        tagReppsitory.save(tag);
        return tag;
    }

    @Override
    public List<Tag> findAll() {
        return tagReppsitory.findAll();
    }

    @Override
    public Tag findById(String id) {
        return tagReppsitory.findById(id).orElseThrow(() -> new ApiExceptionHandler.NotFoundException("Tag not found!"));
    }

    @Override
    public Tag findByName(String name) {
        return tagReppsitory.findByName(name).orElse(null);}

    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public void delete(Tag tag) {
        tagReppsitory.delete(tag);
    }
}
