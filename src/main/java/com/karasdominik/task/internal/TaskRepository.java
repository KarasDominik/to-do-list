package com.karasdominik.task.internal;

import com.karasdominik.task.dto.exception.TaskNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    default Task findOrThrow(UUID taskId) {
        return findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
    }
}
