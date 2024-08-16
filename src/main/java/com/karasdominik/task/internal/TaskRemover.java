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
class TaskRemover {

    private final TaskRepository tasks;
    private final TaskAssertions taskAssertions;

    public void deleteBy(UUID id) {
        log.info("Deleting task {}", id);
        var task = tasks.findOrThrow(id);
        taskAssertions.assertLoggedUserCanAccessTask(task);
        tasks.deleteById(id);
        log.info("Task {} deleted", id);
    }
}
