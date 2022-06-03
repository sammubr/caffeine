package br.com.sammubr.caffeine.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/mono")
    public Mono<String> getMono() {
        return Mono.just("Hello WebFlux");
    }

    @GetMapping("/flux")
    public Flux<Integer> getFlux() {
        return Flux.fromIterable(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    }

}
