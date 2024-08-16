package com.karasdominik.task.internal;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
class TaskUpdater {

    private final TaskRepository tasks;
    private final TaskAssertions assertions;

    public void update(UUID taskId) {
        log.info("Updating status of task {}", taskId);
        var task = tasks.findOrThrow(taskId);
        assertions.assertLoggedUserCanAccessTask(task);
        task.update();
        log.info("Task {} updated", task.id());
    }
}
