package com.karasdominik.common;

import lombok.Getter;

@Getter
public class InvalidFieldException extends RuntimeException{

    private final String message;

    public InvalidFieldException(String message) {
        this.message = message;
    }
}
