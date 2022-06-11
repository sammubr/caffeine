package br.com.sammubr.caffeine;

import org.testcontainers.containers.GenericContainer;

public class MongoContainer extends GenericContainer<MongoContainer> {

    public MongoContainer() {
        super("mongo:4.4.8");
    }

}
