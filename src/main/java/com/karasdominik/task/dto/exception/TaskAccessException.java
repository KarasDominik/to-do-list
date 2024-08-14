package com.karasdominik.task.dto.exception;

import com.karasdominik.common.ConflictedRequestException;

import java.util.UUID;

public class TaskAccessException extends ConflictedRequestException {

    private static final String MESSAGE = "User %s cannot access task %s";

    public TaskAccessException(UUID userId, UUID taskId) {
        super(String.format(MESSAGE, userId, taskId));
    }
}
