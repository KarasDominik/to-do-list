package com.karasdominik.task.web;

import com.karasdominik.task.dto.TaskDto;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

record GetTasksResponse(List<TaskResponse> tasks) {

    static GetTasksResponse of(List<TaskDto> dtos) {
        return new GetTasksResponse(dtos.stream()
                .map(TaskResponse::of)
                .toList());
    }

    @Builder
    record TaskResponse(UUID taskId, String content, boolean done) {

        static TaskResponse of(TaskDto dto) {
            return TaskResponse.builder()
                    .taskId(dto.taskId())
                    .content(dto.content())
                    .done(dto.done())
                    .build();
        }
    }
}
