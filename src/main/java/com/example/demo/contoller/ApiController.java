package com.example.demo.contoller;

import com.example.demo.entity.Coupon;
import com.example.demo.service.CouponService;
import io.lettuce.core.dynamic.annotation.Param;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class ApiController {

    private final CouponService couponService;

    @GetMapping("/todolist")
    ResponseEntity<String> todolist(){
        return ResponseEntity.ok("todolist");
    }

    @GetMapping("/showcoupon")
    ResponseEntity<Coupon> viewCoupon(@RequestParam Long couponId) {
        Coupon coupon = couponService.show(couponId);
        return ResponseEntity.ok(coupon);
    }

    @GetMapping("/usecoupon")
    ResponseEntity<Coupon> viewCounter(@RequestParam Long couponId) {
        Coupon coupon = couponService.decrease(couponId);
        return ResponseEntity.ok(coupon);
    }

    @GetMapping("/searchcoupon")
    List<Coupon> searchCoupon(@RequestParam(required = false) String keyword) {
        return couponService.searchCoupon(keyword);
    }
}
