package br.com.sammubr.caffeine.service;

import br.com.sammubr.caffeine.entity.CarEntity;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"car1"})
public class CarCacheService implements CarInterface {

    Cache<String, Mono<CarEntity>> cache = Caffeine.newBuilder()
            .expireAfterWrite(5, TimeUnit.SECONDS)
            .maximumSize(100)
            .build();

    private final CarService carService;

    @Cacheable
    @Override
    public Mono<CarEntity> findOne(String id) {
        return cache.get(id, k -> carService.findOne(id));
    }

}
