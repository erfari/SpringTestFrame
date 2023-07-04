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
public class UserPutResponse {
    @JsonProperty("name")
    String name;

    @JsonProperty("job")
    String job;

    @JsonProperty("updatedAt")
    String updatedAt;
}