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
public class UsersByIdResponse {
    @JsonProperty("last_name")
    String lastName;

    @JsonProperty("id")
    int id;

    @JsonProperty("avatar")
    String avatar;

    @JsonProperty("first_name")
    String firstName;

    @JsonProperty("email")
    String email;
}