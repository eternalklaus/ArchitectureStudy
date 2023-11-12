package com.example.demo.service;

import com.example.demo.entity.Coupon;
import com.example.demo.lock.DistributeLock;
import com.example.demo.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "default")
public class CouponService {
    private final CouponRepository couponRepository;
    private static final String COUPON_KEY_PREFIX = "COUPON_";

    @CacheEvict(keyGenerator = "couponKeyGenerator")
    public Coupon decrease(Long couponId) {
        String couponName = COUPON_KEY_PREFIX + couponId;
        return safeDecrease(couponName);
    }

    @DistributeLock(key = "#key")
    private Coupon safeDecrease(String key) {
        Coupon coupon = couponRepository.findByName(key);
        coupon.decrease();
        couponRepository.save(coupon);
        return coupon;
    }

    @Cacheable(keyGenerator = "couponKeyGenerator")
    public Coupon show(Long couponId) {
        String couponName = COUPON_KEY_PREFIX + couponId;
        return couponRepository.findByName(couponName);
    }

    public List<Coupon> search(String keyword) {
        Specification<Coupon> spec = (root, query, criteriaBuilder) -> {
            if (keyword == null) {
                return null; // 키워드가 주어지지 않는경우 모든 쿠폰을 리턴
            }
            else {
                return criteriaBuilder.like(root.get("description"), "%"+keyword+"%"); // 키워드가 있는경우 키워드 포함 검색
            }
        };
        return couponRepository.findAll(spec); // 동적으로 생성된 spec을 입력
    }
}
