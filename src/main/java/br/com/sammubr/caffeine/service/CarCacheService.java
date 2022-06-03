package br.com.sammubr.caffeine.service;

import br.com.sammubr.caffeine.entity.CarEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CarCacheService {
    public Mono<CarEntity> findOne(String id) {
        return null;
    }
}
