package br.com.sammubr.caffeine.service;

import br.com.sammubr.caffeine.entity.CarEntity;
import br.com.sammubr.caffeine.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public Mono<CarEntity> save(CarEntity carEntity) {
        return carRepository.save(carEntity);
    }

    public Flux<CarEntity> saveAll(List<CarEntity> carEntities) {
        return carRepository.saveAll(carEntities);
    }

    public Flux<CarEntity> findAll() {
        return carRepository.findAll();
    }

    public Mono<CarEntity> findOne(String id) {
        return carRepository.findById(id);
    }
}
