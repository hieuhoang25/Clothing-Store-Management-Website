package com.hicode.cozastore.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    public Object upload(MultipartFile multipartFile);
    public Object download(String fileName) throws IOException;
    public boolean delete(String fileName) throws IOException;
}
