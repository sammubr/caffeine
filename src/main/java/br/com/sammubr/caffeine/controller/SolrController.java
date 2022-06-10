package br.com.sammubr.caffeine.controller;

import br.com.sammubr.caffeine.entity.CarEntity;
import br.com.sammubr.caffeine.service.SolrService;
import br.com.sammubr.caffeine.webclient.response.SolrResponse;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/solr")
@RequiredArgsConstructor
public class SolrController {

    private final SolrService solrService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<SolrResponse<JsonNode>> selectAll() {
        return solrService.selectAll();
    }

    @GetMapping(value = "/index/car/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<SolrResponse<JsonNode>> indexCar(@PathVariable String id) {
        return solrService.indexCar(id);
    }

}
