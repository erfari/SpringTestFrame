package com.mock.exampleTask.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum WebUrl {
    STAND_URL_1("https://reqres.in/", "test1");

    private final String url;
    private final String stand;

}
