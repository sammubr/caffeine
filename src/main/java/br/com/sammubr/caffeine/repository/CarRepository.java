package br.com.sammubr.caffeine.repository;

import br.com.sammubr.caffeine.entity.CarEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CarRepository extends ReactiveMongoRepository<CarEntity, String> {
}
