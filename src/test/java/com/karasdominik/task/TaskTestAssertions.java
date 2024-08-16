package com.karasdominik.task;

import com.karasdominik.common.TimeProvider;
import com.karasdominik.task.internal.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Component
@RequiredArgsConstructor
public class TaskTestAssertions {

    private final TaskRepository tasks;
    private final TimeProvider timeProvider;

    public void assertTaskCreated(UUID taskId, String content) {
        assertThat(tasks.findById(taskId))
                .isPresent()
                .hasValueSatisfying(task -> {
                    assertThat(task.content()).isEqualTo(content);
                    assertThat(task.done()).isFalse();
                    assertThat(task.createdDate()).isEqualTo(timeProvider.now());
                });
    }

    public void assertTaskDeleted(UUID taskId) {
        assertThat(tasks.findById(taskId))
                .isEmpty();
    }

    public void assertTaskCompleted(UUID taskId) {
        assertThat(tasks.findById(taskId))
                .isPresent()
                .hasValueSatisfying(task -> assertThat(task.done()).isTrue());
    }
}
