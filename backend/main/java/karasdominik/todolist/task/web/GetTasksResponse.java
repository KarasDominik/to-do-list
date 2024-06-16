package main.java.karasdominik.todolist.task.web;

import java.util.List;
import java.util.UUID;

record GetTasksResponse(List<TaskResponse> tasks) {

    record TaskResponse(UUID taskId, String content, boolean done) {}
}
