package br.com.sammubr.caffeine;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.wait.strategy.Wait;

import java.io.IOException;
import java.time.Duration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = {AbstractIntegrationTest.Initializer.class})
@ActiveProfiles("test")
@AutoConfigureMockMvc
public abstract class AbstractIntegrationTest {

    public static MongoContainer mongo = new MongoContainer()
            .withExposedPorts(27017)
            .waitingFor(Wait.forListeningPort().withStartupTimeout(Duration.ofSeconds(800)));

    public static SolrContainer solr = new SolrContainer()
            .withExposedPorts(8983)
            .waitingFor(Wait.forListeningPort().withStartupTimeout(Duration.ofSeconds(800)));

    static {
        mongo.start();
        solr.start();
        try {
            solr.execInContainer("solr", "create", "-c", "caffeine");
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected ObjectMapper objectMapper;

    @BeforeEach
    public void doBefore() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            TestPropertyValues.of(
                    "spring.data.mongodb.host=" + mongo.getHost(),
                    "spring.data.mongodb.port=" + mongo.getMappedPort(27017),
                    "spring.data.mongodb.database=test",
                    "solr.host=" + solr.getHost(),
                    "solr.port=" + solr.getMappedPort(8983)
            ).applyTo(configurableApplicationContext.getEnvironment());
        }

    }

}
