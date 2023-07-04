package com.mock.exampleTask.flows;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BaseResponse<T> {
    @JsonProperty(value = "data")
    private T data;
    @JsonProperty(value = "support")
    private SupportResponse support;

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
