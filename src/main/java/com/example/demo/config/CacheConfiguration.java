package com.example.demo.config;

import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfiguration implements CachingConfigurer {
    // TODO: 스프링 버전에 맞게 래디스 캐시 컨피그 구현하기
}
