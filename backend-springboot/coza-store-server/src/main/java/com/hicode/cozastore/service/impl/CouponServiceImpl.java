package com.hicode.cozastore.service.impl;

import com.hicode.cozastore.collection.Coupon;
import com.hicode.cozastore.repository.CouponRepository;
import com.hicode.cozastore.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;
    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public Coupon insert(Coupon coupon) {
        couponRepository.insert(coupon);
        return coupon;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public Coupon update(Coupon coupon) {
        couponRepository.save(coupon);
        return coupon;
    }

    @Override
    public List<Coupon> findAll() {
        return couponRepository.findAll();
    }

    @Override
    public Coupon findById(String id) {
        return couponRepository.findById(id).orElse(null);
    }

    @Override
    public Coupon findByName(String name) {
        return couponRepository.findByName(name).orElse(null);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public Object delete(Coupon coupon) {
        couponRepository.delete(coupon);
        return null;
    }
}
