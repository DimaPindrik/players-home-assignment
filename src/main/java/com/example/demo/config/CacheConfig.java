package com.example.demo.config;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableCaching
public class CacheConfig {

  @Bean
  public CacheManagerCustomizer<ConcurrentMapCacheManager> cacheManagerCustomizer() {
    return (cacheManager) -> cacheManager.setAllowNullValues(false);
  }
}
