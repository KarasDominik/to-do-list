package com.karasdominik.task.internal;

import com.karasdominik.task.TaskManagement;
import com.karasdominik.task.dto.CreateTaskCommand;
import com.karasdominik.task.dto.SearchTasksQuery;
import com.karasdominik.task.dto.TaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TaskManagementImpl implements TaskManagement {

    private final TaskSearchService searchService;
    private final TaskCreator creator;
    private final TaskUpdater updater;
    private final TaskRemover remover;

    @Override
    public UUID create(CreateTaskCommand command) {
        return creator.create(command);
    }

    @Override
    public List<TaskDto> getAll(SearchTasksQuery query) {
        return searchService.getAll(query);
    }

    @Override
    public void update(UUID taskId) {
        updater.update(taskId);
    }

    @Override
    public void delete(UUID id) {
        remover.deleteBy(id);
    }
}
