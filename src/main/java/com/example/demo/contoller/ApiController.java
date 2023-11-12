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

    // TODO: @Scheduled 어노테이션을 통해 주기적으로 실행되어야 할 스케줄을 정의한다
    // TODO: expired된 쿠폰을 정리하는 이벤트를 카프카에 주기적으로 produce한다
    // TODO: 카프카에서 이벤트를 consume하며 작업을 처리한다
    @PostMapping("/kafka")
    public String kafka(@RequestParam("message") String message) {
        kafkaProducer.produce("testtopic", message);
        return null;
    }
}
