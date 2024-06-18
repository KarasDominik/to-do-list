package com.karasdominik.task.web;

import com.karasdominik.task.dto.CreateTaskCommand;
import lombok.experimental.UtilityClass;

@UtilityClass
class RequestMapper {

    static CreateTaskCommand asCommand(CreateTaskRequest request) {
        return CreateTaskCommand.builder()
                .content(request.content())
                .build();
    }
}
