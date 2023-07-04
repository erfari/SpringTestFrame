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
public class RegisterRequest {

    @JsonProperty("password")
    String password;

    @JsonProperty("email")
    String email;

}