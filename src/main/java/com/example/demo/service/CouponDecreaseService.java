package com.example.demo.service;

import com.example.demo.entity.Coupon;
import com.example.demo.lock.DistributeLock;
import com.example.demo.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CouponDecreaseService {
    private final CouponRepository couponRepository;

    @DistributeLock(key = "#key")
    public Coupon couponDecrease(String key) {
        Coupon coupon = couponRepository.findByName(key);
        coupon.decrease();
        return coupon;
    }
}
