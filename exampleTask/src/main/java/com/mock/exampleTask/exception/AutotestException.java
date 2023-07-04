package com.mock.exampleTask.exception;

import lombok.Getter;

@Getter
public class AutotestException extends RuntimeException {
    private final String exceptionMessage;

    public AutotestException(String exceptionMessage) {
        super(exceptionMessage);
        this.exceptionMessage = exceptionMessage;
    }
}
