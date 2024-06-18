package com.karasdominik.task.internal;

import com.karasdominik.task.dto.TaskDto;
import org.springframework.stereotype.Component;

@Component
class DtoMapper {

    TaskDto asDto(Task task) {
        return TaskDto.builder()
                .taskId(task.id())
                .content(task.content())
                .done(task.done())
                .build();
    }
}
