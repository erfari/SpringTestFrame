package com.mock.exampleTask.flows.resource;

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
public class ResourceByIdResponse {
    @JsonProperty("color")
    String color;

    @JsonProperty("year")
    int year;

    @JsonProperty("name")
    String name;

    @JsonProperty("id")
    int id;
    @JsonProperty("pantone_value")
    String pantoneValue;
}