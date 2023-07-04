package com.mock.exampleTask.flows.register;

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
public class RegisterResponse {
    @JsonProperty("id")
    Integer id;

    @JsonProperty("token")
    String token;
}