package br.com.sammubr.caffeine.webclient.response;

import lombok.Data;

@Data
public class SolrResponse<T> {

    private SolrResponseHeader responseHeader;
    private SolrResponseWrapper<T> response;
    private T grouped;

}
