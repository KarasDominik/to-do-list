package com.karasdominik.task.internal;

import com.karasdominik.task.dto.exception.TaskNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findAllByUserIdOrderByCreatedDateDesc(UUID userId);

    default Task findOrThrow(UUID id) {
        return findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }
}
