package com.example.demo.service;

import com.example.demo.entity.Coupon;
import com.example.demo.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "default")
public class CouponService {
    private final CouponDecreaseService couponDecreaseService;
    private final CouponRepository couponRepository;
    private static final String COUPON_KEY_PREFIX = "COUPON_";

    @CacheEvict(key = "'COUPON_' + #couponId")
    public Coupon decrease(Long couponId) {
        String couponName = COUPON_KEY_PREFIX + couponId;
        return couponDecreaseService.couponDecrease(couponName); // TODO: couponDecreaseService 제거, 서비스 통합
    }

    @Cacheable(key = "'COUPON_' + #couponId") // TODO: 캐시 키 네이밍은 keyGenerator에서 자동으로 설정되도록 구현하기
    public Coupon show(Long couponId) {
        String couponName = COUPON_KEY_PREFIX + couponId;
        return couponRepository.findByName(couponName);
    }
}
