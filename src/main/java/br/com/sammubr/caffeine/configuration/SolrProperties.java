package br.com.sammubr.caffeine.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "solr")
@Getter
@Setter
public class SolrProperties {

    private String host;
    private Long port;

}
