package com.mock.exampleTask.flows;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BaseResponseList<T> {
    @JsonProperty(value = "data")
    private List<T> data;
    @JsonProperty(value = "support")
    private BaseResponse.SupportResponse support;

    @JsonProperty(value = "page")
    private Integer page;
    @JsonProperty(value = "per_page")
    private Integer per_page;
    @JsonProperty(value = "total")
    private Integer total;
    @JsonProperty(value = "total_pages")
    private Integer total_pages;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ToString
    public static class SupportResponse {
        @JsonProperty(value = "url")
        private String url;
        @JsonProperty(value = "text")
        private String text;
    }
}
