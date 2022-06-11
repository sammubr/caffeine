package br.com.sammubr.caffeine.webclient;

import br.com.sammubr.caffeine.configuration.SolrProperties;
import br.com.sammubr.caffeine.entity.CarEntity;
import br.com.sammubr.caffeine.webclient.request.SolrSelectRequest;
import br.com.sammubr.caffeine.webclient.response.SolrResponse;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Service
public class SolrClient {

    private final WebClient webClient;

    public SolrClient(SolrProperties solrProperties) {
        WebClient teste = WebClient.builder()
                .baseUrl("http://" + solrProperties.getHost() + ":" + solrProperties.getPort() + "/solr")
                .build();
        this.webClient = teste;
    }

    public Mono<SolrResponse<JsonNode>> select(SolrSelectRequest solrRequest) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/{collection}/select").build("caffeine"))
//                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(solrRequest), SolrSelectRequest.class)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
    }

//    public Mono<ResponseEntity<Void>> update(CarEntity solrDocument) {
//        return webClient.post()
//                .uri(uriBuilder -> uriBuilder.path("/{collection}/update/json/docs").build("caffeine"))
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(Mono.just(solrDocument), CarEntity.class)
//                .retrieve()
//                .toBodilessEntity();
//    }

    public Mono<ResponseEntity<Void>> update(CarEntity solrDocument) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/{collection}/update")
                        .queryParam("commit", true)
//                        .queryParam("overwrite", true)
//                        .queryParam("wt", "json")
                        .build("caffeine"))
                .contentType(MediaType.APPLICATION_JSON)
                .body(Flux.fromIterable(Collections.singleton(solrDocument)), CarEntity.class)
                .retrieve()
                .toBodilessEntity();
    }

    public Mono<ResponseEntity<Void>> deleteAllDocuments() {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/{collection}/update")
                        .queryParam("commit", true)
                        .build("caffeine"))
                .contentType(MediaType.TEXT_XML)
                .body(Mono.just("<delete><query>*:*</query></delete>"), String.class)
                .retrieve()
                .toBodilessEntity();
    }

}
