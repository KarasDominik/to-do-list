package com.karasdominik.task;

import com.karasdominik.task.dto.CreateTaskCommand;
import com.karasdominik.task.dto.SearchTasksQuery;
import com.karasdominik.task.dto.TaskDto;

import java.util.List;
import java.util.UUID;

public interface TaskManagement {

    UUID create(CreateTaskCommand command);

    List<TaskDto> getAll(SearchTasksQuery query);

    void update(UUID taskId);

    void delete(UUID id);
}
