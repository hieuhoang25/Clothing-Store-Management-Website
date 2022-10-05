package com.hicode.cozastore.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class User {
    private String id;

    @Indexed(unique = true)
    private String username;
    private String password;
    private String fullname;
    private String email;
    private String address;
    private String phone;
    private String image;
    @DocumentReference
    private List<Role> roles = new ArrayList<>();
    @DocumentReference
    private List<Order> orders = new ArrayList<>();
}
