package com.example.demo.contoller;

import com.example.demo.broker.KafkaProducer;
import com.example.demo.entity.Coupon;
import com.example.demo.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class ApiController {

    private final CouponService couponService;
    private final KafkaProducer kafkaProducer;

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
        return couponService.search(keyword);
    }

    @PostMapping("/kafka")
    public String kafka(@RequestParam("message") String message) {
        kafkaProducer.produce("testtopic", message);
        return null;
    }

    @PostMapping("/test")
    public String test(@RequestBody String body) {
        System.out.println(body);
        return null;
    }
}
