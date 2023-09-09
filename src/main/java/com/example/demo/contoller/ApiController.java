package com.example.demo.contoller;

import com.example.demo.entity.Coupon;
import com.example.demo.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class ApiController {

    private final CouponService couponService;

    @GetMapping("/todolist")
    ResponseEntity<String> todolist(){
        return ResponseEntity.ok("todolist");
    }

    @GetMapping("/viewcoupon")
    ResponseEntity<String> viewCoupon() {
        // TODO: 사용자 정보를 바탕으로 쿠폰을 가져온다
        // TODO: 로컬의 래디스캐시에 쿠폰리스트가 저장되도록 최적화한다
        return ResponseEntity.ok("당신의 쿠폰 리스트는: ");
    }

    @GetMapping("/usecoupon")
    ResponseEntity<Coupon> viewCounter(){
        Coupon coupon = couponService.decrease(1L);
        return ResponseEntity.ok(coupon);
    }
}
