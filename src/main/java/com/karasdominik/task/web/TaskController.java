package com.karasdominik.task.web;

import com.karasdominik.common.LoggedUserProvider;
import com.karasdominik.task.TaskManagement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.karasdominik.task.web.RequestMapper.asCommand;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/task")
@CrossOrigin("*")
class TaskController {

    private final TaskManagement management;
    private final LoggedUserProvider loggedUserProvider;

    @GetMapping
    GetTasksResponse getAll() {
        return GetTasksResponse.of(management.getAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CreateTaskResponse create(@RequestBody CreateTaskRequest request) {
        var loggedUser = loggedUserProvider.getLoggedUser();
        return CreateTaskResponse.of(management.create(asCommand(request, loggedUser.id())));
    }

    @PutMapping("{taskId}")
    void update(@PathVariable UUID taskId) {
        management.update(taskId);
    }

    @DeleteMapping("{taskId}")
    void delete(@PathVariable UUID taskId) {
        management.delete(taskId);
    }
}
