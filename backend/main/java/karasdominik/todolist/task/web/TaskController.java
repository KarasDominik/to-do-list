package main.java.karasdominik.todolist.task.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import main.java.karasdominik.todolist.task.web.GetTasksResponse.TaskResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/task")
@Slf4j
class TaskController {

    @GetMapping
    GetTasksResponse getAll() {
        log.info("Fetching all tasks");
        return new GetTasksResponse(
                List.of(
                        new TaskResponse(randomUUID(), "Finish first app to the end", false),
                        new TaskResponse(randomUUID(), "Lose 10 pounds", false),
                        new TaskResponse(randomUUID(), "Take and finish linux masterclass course", false)
                )
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CreateTaskResponse create(@RequestBody CreateTaskRequest request) {
        log.info("Creating new task");
        return new CreateTaskResponse(randomUUID());
    }

    @PutMapping("{taskId}")
    void update(@PathVariable UUID taskId) {
        log.info("Updating task {}", taskId);
    }

    @DeleteMapping("{taskId}")
    void delete(@PathVariable UUID taskId) {
        log.info("Deleting task {}", taskId);
    }
}
