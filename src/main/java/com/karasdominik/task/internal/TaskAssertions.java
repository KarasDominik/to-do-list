package com.karasdominik.task.internal;

import com.karasdominik.common.LoggedUserProvider;
import com.karasdominik.task.dto.exception.TaskAccessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class TaskAssertions {

    private final LoggedUserProvider loggedUserProvider;

    void assertLoggedUserCanAccessTask(final Task task) {
        var loggedUser = loggedUserProvider.getLoggedUser();
        if (!loggedUser.id().equals(task.user().id())) {
            throw new TaskAccessException(loggedUser.id(), task.id());
        }
    }
}
