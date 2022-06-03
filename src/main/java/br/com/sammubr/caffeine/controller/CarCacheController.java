package br.com.sammubr.caffeine.controller;

import br.com.sammubr.caffeine.entity.CarEntity;
import br.com.sammubr.caffeine.service.CarCacheService;
import br.com.sammubr.caffeine.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/cache/car")
@RequiredArgsConstructor
public class CarCacheController {

    private final CarCacheService carCacheService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<CarEntity> findAll(@PathVariable String id) {
        return carCacheService.findOne(id);
    }

}
