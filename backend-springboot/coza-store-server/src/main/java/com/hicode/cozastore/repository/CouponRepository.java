package com.hicode.cozastore.repository;

import com.hicode.cozastore.collection.Coupon;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CouponRepository extends MongoRepository<Coupon,String> {
    Optional<Coupon> findByName(String name);
}
