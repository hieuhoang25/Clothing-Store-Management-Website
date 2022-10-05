package com.hicode.cozastore.graphql;

import com.hicode.cozastore.collection.*;
import com.hicode.cozastore.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
@Controller
@RequiredArgsConstructor
public class SchemaController {
    private final ColorService colorService;
    private final CategoryService categoryService;
    private final SizeService sizeService;
    private final ProductService productService;
    private final TagService tagService;


    @QueryMapping
    Iterable<Color> colors(){
        return colorService.findAll();
    }

    @QueryMapping
    Iterable<Category> categories(){
        return categoryService.findAll();
    }

    @QueryMapping
    Iterable<Tag> tags(){
        return tagService.findAll();
    }

    @QueryMapping
    Iterable<Size> sizes(){
        return sizeService.findAll();
    }
    @QueryMapping
    Iterable<Product> products(){
        return productService.findAll();
    }

}
