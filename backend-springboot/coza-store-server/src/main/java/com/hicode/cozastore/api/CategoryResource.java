package com.hicode.cozastore.api;

import com.hicode.cozastore.collection.Category;
import com.hicode.cozastore.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CategoryResource {
    private final CategoryService categoryService;

    @PostMapping("admin/categories")
    public ResponseEntity<?> insert(@RequestBody Category category) {
        if (categoryService.findByName(category.getName()) != null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category already exits");
        return ResponseEntity.ok(categoryService.insert(category));
    }

    @PutMapping("admin/categories")
    public ResponseEntity<?> update(@RequestBody Category category) {
        if (categoryService.findById(category.getId()) == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
        return ResponseEntity.ok(categoryService.update(category));
    }

    @DeleteMapping("admin/categories/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        if (categoryService.findById(id) == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found");
        categoryService.delete(categoryService.findById(id));
        return ResponseEntity.ok().build();
    }
}
