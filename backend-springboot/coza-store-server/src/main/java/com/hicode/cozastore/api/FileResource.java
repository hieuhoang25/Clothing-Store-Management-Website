package com.hicode.cozastore.api;

import com.hicode.cozastore.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1")
public class FileResource {
    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<Object> upload(@RequestPart("file") MultipartFile multipartFile) {
        return ResponseEntity.ok(fileService.upload(multipartFile));
    }


    @DeleteMapping("/files/{url}")
    public ResponseEntity<?> delete(@PathVariable("url") String url) {
        try {
            if (fileService.delete(url) == true)
                return ResponseEntity.ok("Delete successfully!");
            else return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Delete failed!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delete failed!");
        }

    }

}