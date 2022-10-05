package com.hicode.cozastore.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {

    @Id
    private String id;
    private Integer quality;
    private Product product;
    private Color colorBuy;
    private Size sizeBuy;

}
