package com.example.demo.lock;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributeLock {
    String key(); // 락의 이름

    TimeUnit timeUnit() default TimeUnit.SECONDS; // 시간 단위

    long waitTime() default 5L; // 최대 5초까지만 락 대기가능

    long leaseTime() default 3L; // 최대 3초까지만 락 소유가능
}


