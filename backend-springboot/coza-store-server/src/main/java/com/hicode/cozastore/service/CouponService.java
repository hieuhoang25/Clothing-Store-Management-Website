package com.hicode.cozastore.service;

import com.hicode.cozastore.collection.Coupon;

import java.util.List;

public interface CouponService {
    Coupon insert(Coupon coupon);
    Coupon update(Coupon coupon);
    List<Coupon> findAll();
    Coupon findById(String id);
    Coupon findByName(String name);
    Object delete(Coupon coupon);
}
