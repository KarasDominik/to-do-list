package com.karasdominik.task.web;

import com.karasdominik.task.dto.CreateTaskCommand;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
class RequestMapper {

    static CreateTaskCommand asCommand(CreateTaskRequest request, UUID id) {
        return CreateTaskCommand.builder()
                .content(request.content())
                .userId(id)
                .build();
    }
}
