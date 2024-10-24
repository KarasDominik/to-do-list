package com.karasdominik.task.internal;

import com.karasdominik.task.dto.SearchTasksQuery;
import com.karasdominik.task.dto.TaskDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TaskSearchService {

    private final TaskRepository tasks;
    private final DtoMapper mapper;

    public List<TaskDto> getAll(SearchTasksQuery query) {
        log.info("Fetching all tasks for user {}", query.userId());
        return tasks.findAllByUserIdOrderByCreatedDateDesc(query.userId()).stream()
                .map(mapper::asDto)
                .toList();
    }
}
