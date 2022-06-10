package br.com.sammubr.caffeine.webclient.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SolrResponseHeader {

    private Boolean zkConnected;
    private Long status;
    @JsonProperty("QTime")
    private Long QTime;
    private Params params;

    @Data
    private static class Params {
        private String json;
    }

}
