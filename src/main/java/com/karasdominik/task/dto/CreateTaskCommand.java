package com.karasdominik.task.dto;

import lombok.Builder;

import static com.karasdominik.common.TaskUtil.maxLength;
import static com.karasdominik.common.TaskUtil.notBlank;

@Builder
public record CreateTaskCommand(String content) {

    public CreateTaskCommand {
        notBlank(content);
        maxLength(content);
    }
}
