package br.com.sammubr.caffeine.webclient.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SolrSelectRequest {

    @Builder.Default
    private String query = "*:*";
    private String filter;
    private String offset;
    private String limit;
    private String fields;
    private String sort;
    private String facet;

}
