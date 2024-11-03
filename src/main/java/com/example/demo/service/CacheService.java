package com.example.demo.service;

import com.example.demo.model.PlayerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CacheService<T> {

  public final static String CACHE_NAME = "players";
  public final static String CACHE_KEY = "allPlayers";

  private final CacheManager cacheManager;

  public boolean isCacheEmpty() {
    ConcurrentMapCache playersCache = (ConcurrentMapCache) cacheManager.getCache(CACHE_NAME);
    if (playersCache != null) {
      return playersCache.getNativeCache().isEmpty();  // Checks if the internal map is empty
    }
    return true;
  }

  public List<T> getCachedResult() {
    Cache cache = cacheManager.getCache(CACHE_NAME);
    if (cache != null) {
      Cache.ValueWrapper valueWrapper = cache.get(CACHE_KEY);
      if (valueWrapper != null) {
        return (List<T>) valueWrapper.get();
      }
    }
    return Collections.emptyList();
  }
}
