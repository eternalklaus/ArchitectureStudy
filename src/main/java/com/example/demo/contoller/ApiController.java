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

    @GetMapping("/showcoupon")
    ResponseEntity<Coupon> viewCoupon() {
        Coupon coupon = couponService.show(1L);
        return ResponseEntity.ok(coupon);
    }

    @GetMapping("/usecoupon")
    ResponseEntity<Coupon> viewCounter() {
        Coupon coupon = couponService.decrease(1L);
        return ResponseEntity.ok(coupon);
    }
}
