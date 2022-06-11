package br.com.sammubr.caffeine.service;

import br.com.sammubr.caffeine.entity.CarEntity;
import br.com.sammubr.caffeine.webclient.SolrClient;
import br.com.sammubr.caffeine.webclient.request.SolrDocument;
import br.com.sammubr.caffeine.webclient.request.SolrSelectRequest;
import br.com.sammubr.caffeine.webclient.response.SolrResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class SolrService {

    private final SolrClient solrWebClient;
    private final CarService carService;

    public Mono<SolrResponse<JsonNode>> selectAll() {
        SolrSelectRequest solrSelectRequest = SolrSelectRequest.builder()
                .build();
        return solrWebClient.select(solrSelectRequest);
    }

    public Mono<ResponseEntity<Void>> indexCar(String id) {
        return carService.findOne(id)
                .flatMap(carEntity -> {
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("register", carEntity.getClass().getName());
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.registerModule(new JavaTimeModule());
                    HashMap<String, Object> map2 = objectMapper.convertValue(carEntity, HashMap.class);
                    map.putAll(map2);
                    return solrWebClient.update(carEntity);
                });
    }
}
