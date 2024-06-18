package com.karasdominik.task.dto.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class InvalidContentException extends RuntimeException {

    private final String message;
}
