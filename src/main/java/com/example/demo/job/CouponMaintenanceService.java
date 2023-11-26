package com.example.demo.job;

import com.example.demo.entity.Coupon;
import com.example.demo.repository.CouponRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Service
@AllArgsConstructor
public class CouponMaintenanceService {
    CouponRepository couponRepository;

    @Scheduled(fixedDelayString = "#{T(java.time.Duration).ofMinutes(1).toMillis()}") // => 매 1분마다 청소
    public void deleteExpiredCoupons() {
        LocalDate localDate = LocalDate.now();
        couponRepository.deleteByEndDateBefore(localDate);
    }

    @Scheduled(cron = "0 0 0 1 * *") // 초 분 시 일 월 요일 => 매달 1일 자정에 쿠폰 채워넣기
    public void issueNewCoupons() {
        System.out.println("haha!!!!!");
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.withDayOfMonth(YearMonth.from(startDate).lengthOfMonth());
        String CouponDesc = startDate.getYear() + "년 " + startDate.getMonthValue() + "월 특별 쿠폰";
        String CouponName = "COUPON_TEMP_" + startDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        Coupon monthlyCoupons = Coupon.builder()
                .availableStock(100L)
                .name(CouponName)
                .description(CouponDesc)
                .startDate(startDate)
                .endDate(endDate)
                .build();
        couponRepository.save(monthlyCoupons);

        monthlyCoupons.setName("COUPON_" + monthlyCoupons.getId()); // 캐시 탈 수 있도록 쿠폰이름 변경
        couponRepository.save(monthlyCoupons);
    }
}
