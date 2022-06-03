package br.com.sammubr.caffeine.controller;

import br.com.sammubr.caffeine.entity.CarEntity;
import br.com.sammubr.caffeine.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<CarEntity> save(@RequestBody CarEntity carEntity) {
        return carService.save(carEntity);
    }

    @PostMapping(value = "/saveAll", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Flux<CarEntity> save(@RequestBody List<CarEntity> carEntities) {
        return carService.saveAll(carEntities);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<CarEntity> findAll() {
        return carService.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<CarEntity> findAll(@PathVariable String id) {
        return carService.findOne(id);
    }

}
