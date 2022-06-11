package br.com.sammubr.caffeine;

import br.com.sammubr.caffeine.configuration.SolrProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({SolrProperties.class})
public class CaffeineApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaffeineApplication.class, args);
    }

}
