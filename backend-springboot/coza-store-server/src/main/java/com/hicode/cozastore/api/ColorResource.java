package com.hicode.cozastore.api;

import com.hicode.cozastore.collection.Color;
import com.hicode.cozastore.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
@CrossOrigin("*")
public class ColorResource {
    private final ColorService colorService;
    @PostMapping("admin/colors")
    public ResponseEntity<?> addColor(@RequestBody Color color){
        if (colorService.findByName(color.getName())!=null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Color already exist");
        }
        return ResponseEntity.ok(colorService.insert(color));
    }
    @PutMapping("admin/colors")
    public ResponseEntity<?> updateColor(@RequestBody Color color){
        if (colorService.findById(color.getId())==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Color not found");
        }
        return ResponseEntity.ok(colorService.update(color));
    }
    @DeleteMapping("admin/colors/{id}")
    public ResponseEntity<?> deleteColor(@PathVariable("id")String id){
        if (colorService.findById(id)==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Color not found");
        }
        colorService.delete(colorService.findById(id));
        return ResponseEntity.ok().build();
    }

}