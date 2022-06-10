package br.com.sammubr.caffeine.webclient.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SolrResponseWrapper<K> {

    private Long numFound;
    private Long start;
    private List<K> docs = new ArrayList<>();

}
