package com.karasdominik.useraccount.dto.exception;

import lombok.Getter;

@Getter
public class EmailAlreadyUsedException extends RuntimeException {

    private final String message;

    public EmailAlreadyUsedException(String email) {
        this.message = String.format("Email %s is already in use", email);
    }
}
