package com.hicode.cozastore.collection;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    private String id;
    private String name;
    private double price;
    private int discount;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate = LocalDateTime.now();
    private String mainImage;
    private List<Product_Sizes> product_sizes;
    private List<Product_Colors> product_colors;
    @DocumentReference
    Category category;
}
@Data
@AllArgsConstructor
class Product_Sizes{
    private Size size;
    private int quality;
}
@Data
@NoArgsConstructor
class Product_Colors{
    private Color color;
    private String image;
}