package br.com.sammubr.caffeine.controller;

import br.com.sammubr.caffeine.entity.CarEntity;
import br.com.sammubr.caffeine.service.CarCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

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
