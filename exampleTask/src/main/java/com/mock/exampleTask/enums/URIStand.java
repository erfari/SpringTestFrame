package com.mock.exampleTask.enums;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum URIStand {
    USERS("api/users"),
    UNKNOWN("api/unknown"),
    REGISTER("api/register");

    @NonNull
    private final String uri;

    public static String getURI(URIStand flow) {
        return Arrays.stream(values())
                .filter(val -> val.equals(flow))
                .findFirst().orElseThrow().getUri();
    }
}
