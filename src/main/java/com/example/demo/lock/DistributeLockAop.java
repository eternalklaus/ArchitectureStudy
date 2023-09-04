package com.example.demo.lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class DistributeLockAop {
    private static final String REDISSON_KEY_PREFIX = "RLOCK_";
    private final RedissonClient redissonClient;
    private final AopForTransaction aopForTransaction;


    @Around("@annotation(com.example.demo.lock.DistributeLock)") // @DistributeLock이 붙은 메서드 실행 전 분산 락을 얻고 -> 메서드 실행하고 -> 락을 해지한다
    public Object lock(final ProceedingJoinPoint joinPoint) throws Throwable { // ProceedingJoinPoint: 대상 메서드가 호출되기 전/후에 추가적 로직을 수행할 수 있도록 함
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DistributeLock distributeLock = method.getAnnotation(DistributeLock.class); // @DistributeLock 어노테이션을 가져옴

        String key = REDISSON_KEY_PREFIX + CustomSpringELParser.getDynamicValue(signature.getParameterNames(), joinPoint.getArgs(), distributeLock.key()); // @DistributeLock에 전달한 key를 파싱

        RLock rLock = redissonClient.getLock(key);

        try {
            boolean available = rLock.tryLock(distributeLock.waitTime(), distributeLock.leaseTime(), distributeLock.timeUnit()); // TODO: 락 획득 실패시 재시도 로직 구현하기 (현재는 락을 획득하지 못하면 false 리턴)
            if (!available) {
                return false;
            }
            log.info("get lock success {}" , key);
            return aopForTransaction.proceed(joinPoint); // @DistributeLock이 선언된 메서드를 실행 (별도 트랜젝션으로 분리함)
        } catch (Exception e) {
            Thread.currentThread().interrupt();
            throw new InterruptedException();
        } finally {
            rLock.unlock(); // finally 에서 lock을 해제한다
        }
    }
}