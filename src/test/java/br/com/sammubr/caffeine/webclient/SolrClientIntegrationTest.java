package br.com.sammubr.caffeine.webclient;

import br.com.sammubr.caffeine.AbstractIntegrationTest;
import br.com.sammubr.caffeine.entity.CarEntity;
import br.com.sammubr.caffeine.webclient.request.SolrSelectRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

class SolrClientIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private SolrClient solrClient;

    @BeforeEach
    public void doBeforeEach() {
        solrClient.deleteAllDocuments().block();
    }


    @Test
    void test1() {
        Assertions.assertEquals(2, 1 + 1);
    }

    @Test
    void test2() {
        StepVerifier.create(solrClient.select(SolrSelectRequest.builder().build()))
                .assertNext(Assertions::assertNotNull)
                .expectComplete()
                .verify();
    }

    @Test
    void test3() {
        StepVerifier.create(solrClient.update(CarEntity.builder().id("456").description("Camaro").build()))
                .assertNext(Assertions::assertNotNull)
                .expectComplete()
                .verify();
    }

    @Test
    void test4() {
        solrClient.update(CarEntity.builder().id("456").description("Camaro").build()).block();
        StepVerifier.create(solrClient.select(SolrSelectRequest.builder().build()))
                .assertNext(Assertions::assertNotNull)
                .expectComplete()
                .verify();
    }

    @Test
    void test5() {
        solrClient.update(CarEntity.builder().id("456").description("Camaro").build()).block();
        StepVerifier.create(solrClient.select(SolrSelectRequest.builder().build()))
                .assertNext(Assertions::assertNotNull)
                .expectComplete()
                .verify();
        StepVerifier.create(solrClient.select(SolrSelectRequest.builder().build()))
                .assertNext(Assertions::assertNotNull)
                .expectComplete()
                .verify();

    }

}