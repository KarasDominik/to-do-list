package com.karasdominik.task;

import com.karasdominik.task.TasksForTests.TaskData;
import com.karasdominik.task.internal.Task;
import com.karasdominik.task.internal.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskFixtures {

    private final TaskRepository tasks;

    public void setUp() {
        createTask(TasksForTests.FINISH_APP);
        createTask(TasksForTests.LEARN_REACT);
        createTask(TasksForTests.WRITE_INTEGRATION_TESTS);
    }

    public void tearDown() {
        tasks.deleteAll();
    }

    private void createTask(TaskData taskData) {
        var task = Task.builder()
                .id(taskData.taskId())
                .content(taskData.content())
                .done(taskData.done())
                .build();
        tasks.save(task);
    }
}
