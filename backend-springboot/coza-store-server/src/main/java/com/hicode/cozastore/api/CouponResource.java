package com.hicode.cozastore.api;

import com.hicode.cozastore.collection.Coupon;
import com.hicode.cozastore.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
@CrossOrigin("*")
public class CouponResource {
    private final CouponService couponService;

    @GetMapping("admin/coupons")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(couponService.findAll());
    }

    @GetMapping("admin/coupons/{id}")
    public ResponseEntity<?> getCoupon(@PathVariable("id") String id) {
        if (couponService.findById(id) == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Coupon not found");
        return ResponseEntity.ok(couponService.findById(id));
    }

    @GetMapping("coupons/{name}")
    public ResponseEntity<?> getCouponByName(@PathVariable("name") String name) {
        if (couponService.findByName(name) == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Coupon not found");
        else {
            Coupon coupon = couponService.findByName(name);
            if (coupon.getExp().isAfter(LocalDateTime.now())) {
                return ResponseEntity.status(HttpStatus.OK).body(coupon.getDiscount());
            } else
                return ResponseEntity.status(HttpStatus.OK).body(-1);//exp
        }
    }

    @PostMapping("admin/coupons")
    public ResponseEntity<?> insert(@RequestBody Coupon coupon) {
        if (couponService.findByName(coupon.getName()) != null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Coupon already exits");
        return ResponseEntity.ok(couponService.insert(coupon));
    }

    @PutMapping("admin/coupons")
    public ResponseEntity<?> update(@RequestBody Coupon coupon) {
        if (couponService.findById(coupon.getId()) == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Coupon not found");
        return ResponseEntity.ok(couponService.update(coupon));
    }

    @DeleteMapping("admin/coupons/{id}")
    public Object delete(@PathVariable("id") String id) {
        if (couponService.findById(id) == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Coupon not found");
        couponService.delete(couponService.findById(id));
        return ResponseEntity.ok().build();
    }
}

