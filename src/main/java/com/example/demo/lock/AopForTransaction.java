package com.example.demo.lock;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AopForTransaction { // Aop내에서 트랜잭션을 별도로 가져가기 위해 선언(?)

    @Transactional(propagation = Propagation.REQUIRES_NEW) // 부모트랜젝션 유무와 관계없이 별도의 트랜젝션으로 동작하도록
    public Object proceed(final ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed();
    }
}