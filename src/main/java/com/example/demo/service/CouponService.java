package com.example.demo.service;

import com.example.demo.entity.Coupon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponDecreaseService couponDecreaseService;

    private static final String COUPON_KEY_PREFIX = "COUPON_";

    public Coupon decrease(Long couponId) {
        String couponName = COUPON_KEY_PREFIX + couponId;
        return couponDecreaseService.couponDecrease(couponName);
    }
}
