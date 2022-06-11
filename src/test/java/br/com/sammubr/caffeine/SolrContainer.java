package br.com.sammubr.caffeine;

import org.testcontainers.containers.GenericContainer;

public class SolrContainer extends GenericContainer<SolrContainer> {

    public SolrContainer() {
        super("solr:8.5.1");
    }

}
