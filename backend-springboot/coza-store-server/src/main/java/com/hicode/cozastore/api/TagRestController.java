package com.hicode.cozastore.api;

import com.hicode.cozastore.collection.Tag;
import com.hicode.cozastore.exception.ApiExceptionHandler;
import com.hicode.cozastore.service.TagService;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("api/v1")
//@Validated
public class TagRestController {
    private final TagService tagService;

    @GetMapping("tags/{id}")
    public ResponseEntity<?> getTag(@PathVariable("id") /* @Length(min = 3,max = 6, message = "Length from 3 to 5")*/ String id) {
        return ResponseEntity.ok(tagService.findById(id));
    }

    @PostMapping("admin/tags")
    public ResponseEntity<?> insert(@RequestBody Tag tag) {
        if (tagService.findByName(tag.getName()) != null) {
            throw new ApiExceptionHandler.BadRequestException("Tag already exits");
        }
        else return ResponseEntity.ok(tagService.insert(tag));
    }

    @PutMapping("admin/tags")
    public ResponseEntity<?> update(@RequestBody Tag tag) {
        if (tagService.findById(tag.getId()) == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tag not found");
        return ResponseEntity.ok(tagService.update(tag));
    }

    @DeleteMapping("admin/tags/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        if (tagService.findById(id) == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tag not found");
        tagService.delete(tagService.findById(id));
        return ResponseEntity.ok().build();
    }
}
