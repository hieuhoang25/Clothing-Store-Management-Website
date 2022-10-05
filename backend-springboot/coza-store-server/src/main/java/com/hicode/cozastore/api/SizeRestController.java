package com.hicode.cozastore.api;

import com.hicode.cozastore.collection.Size;
import com.hicode.cozastore.service.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
@CrossOrigin("*")
public class SizeRestController {
    private final SizeService sizeService;


    @GetMapping("sizes/{id}")
    public ResponseEntity<?> getSize(@PathVariable("id") String id) {
        if (sizeService.findById(id) == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Size not found");
        return ResponseEntity.ok(sizeService.findById(id));
    }

    @PostMapping("admin/sizes")
    public ResponseEntity<?> insert(@RequestBody Size size) {
        if (sizeService.findByName(size.getName())!=null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Size already exits");
        return ResponseEntity.ok(sizeService.insert(size));
    }
    @PutMapping("admin/sizes")
    public ResponseEntity<?> update(@RequestBody Size size){
        if (sizeService.findById(size.getId())==null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Size not found");
        return ResponseEntity.ok(sizeService.update(size));
    }
    @DeleteMapping("admin/sizes/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id){
        if (sizeService.findById(id)==null)  return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Size not found");
        sizeService.delete(sizeService.findById(id));
        return ResponseEntity.ok().build();
    }
}
