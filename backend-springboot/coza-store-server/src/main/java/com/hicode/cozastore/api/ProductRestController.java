package com.hicode.cozastore.api;

import com.hicode.cozastore.collection.Product;

import com.hicode.cozastore.exception.ApiExceptionHandler;
import com.hicode.cozastore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductService productService;

    @GetMapping("products/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") String id) {
        Product product = productService.findById(id);
        if (product == null) throw new ApiExceptionHandler.NotFoundException("Product not found");
        else return ResponseEntity.ok(product);
    }

    @GetMapping("products/name/{name}")
    public ResponseEntity<?> getOneByName(@PathVariable("name") String name) {
        Product product = productService.findByName(name);
        if (product == null) throw new ApiExceptionHandler.NotFoundException("Product not found");
        return ResponseEntity.ok(product);
    }

    @PostMapping("admin/products")
    public ResponseEntity<?> insert(@RequestBody Product product) {
        if (productService.findByName(product.getName()) != null)
            throw new ApiExceptionHandler.BadRequestException("Product already exits");
        else
            return ResponseEntity.ok(productService.insert(product));
    }

    @PutMapping("admin/products")
    public ResponseEntity<?> update(@RequestBody Product product) {
        if(productService.findById(product.getId())==null) throw new ApiExceptionHandler.NotFoundException("Product not found");
        else
            return ResponseEntity.ok(productService.update(product));
    }

    @DeleteMapping("admin/products/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id) {
        if (productService.findById(id) == null) throw new ApiExceptionHandler.NotFoundException("Product not found");
        else {
            productService.delete(productService.findById(id));
            return ResponseEntity.ok().body("Delete successfully");
        }
    }
}
