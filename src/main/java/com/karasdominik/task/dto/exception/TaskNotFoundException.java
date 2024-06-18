package com.karasdominik.task.dto.exception;

import lombok.Getter;

import java.util.UUID;

@Getter
public class TaskNotFoundException extends RuntimeException {

    private final String message;

    public TaskNotFoundException(UUID taskId) {
        this.message = String.format("Task with id %s not found", taskId);
    }
}
