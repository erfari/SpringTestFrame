package com.mock.exampleTask.flows.users;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UsersByIdSupportResponse {
    @JsonProperty("url")
    String url;

    @JsonProperty("text")
    String text;
}