package com.karasdominik.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class ConflictedRequestException extends RuntimeException {

    private final String message;
}
