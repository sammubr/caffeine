package br.com.sammubr.caffeine.webclient;

import br.com.sammubr.caffeine.entity.CarEntity;
import br.com.sammubr.caffeine.webclient.request.SolrDocument;
import br.com.sammubr.caffeine.webclient.request.SolrSelectRequest;
import br.com.sammubr.caffeine.webclient.response.SolrResponse;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class SolrClient {

    private final WebClient webClient;

    public SolrClient() {
        webClient = WebClient.builder()
                .baseUrl("http://localhost:8983/solr")
                .build();
    }

    public Mono<SolrResponse<JsonNode>> select(SolrSelectRequest solrRequest) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/{collection}/select").build("caffeine"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(solrRequest), SolrSelectRequest.class)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
    }

    public Mono<SolrResponse<JsonNode>> update(CarEntity solrDocument) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/{collection}/update/json/docs").build("caffeine"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(solrDocument), CarEntity.class)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
    }

}
