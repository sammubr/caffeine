package br.com.sammubr.caffeine.service;

import br.com.sammubr.caffeine.entity.CarEntity;
import reactor.core.publisher.Mono;

public interface CarInterface {
    Mono<CarEntity> findOne(String id);
}
