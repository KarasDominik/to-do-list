package com.karasdominik.task.internal;

import com.karasdominik.common.TimeProvider;
import com.karasdominik.task.dto.CreateTaskCommand;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
class TaskCreator {

    private final TaskRepository tasks;
    private final TimeProvider timeProvider;

    public UUID create(CreateTaskCommand command) {
        log.info("Creating new task");
        var task = tasks.save(Task.create(command, timeProvider::now));
        log.info("Task {} created", task.id());
        return task.id();
    }
}
