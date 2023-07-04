package com.mock.exampleTask.enums;

import com.mock.exampleTask.exception.AutotestException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

import static java.lang.String.format;

@RequiredArgsConstructor
@Getter
public enum HostStand {
    STAND_REST_1("https://reqres.in/", "regress");

    private final String host;
    private final String stand;
    private Type type;

    public static String getActiveHost(String stand, Type type) {
        return Arrays.stream(values())
                .filter(val -> val.stand.equals(stand))
                .findFirst()
                .orElseThrow(() -> new AutotestException(format("Host для %s не найден", stand)))
                .host;
    }
}
