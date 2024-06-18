package com.karasdominik.task.web;

import java.util.UUID;

record CreateTaskResponse(UUID taskId) {

    static CreateTaskResponse of(UUID id) {
        return new CreateTaskResponse(id);
    }
}
