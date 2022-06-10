package br.com.sammubr.caffeine.webclient.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class SolrDocument<T> extends HashMap<String, Object> {

    public SolrDocument(T entity) {
        this.put("register", entity.getClass().getName());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        HashMap<String, Object> map = objectMapper.convertValue(entity, HashMap.class);
        this.putAll(map);
    }

}
