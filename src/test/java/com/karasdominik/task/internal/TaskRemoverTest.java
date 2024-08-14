package com.karasdominik.task.internal;

import com.karasdominik.task.dto.exception.TaskAccessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskRemoverTest {

    @Mock
    private TaskRepository tasks;
    @Mock
    private TaskAssertions assertions;

    @InjectMocks
    private TaskRemover testee;

    @Test
    void shouldNotRemoveWhenLoggedUserIsNotTaskOwner() {
        // given
        var taskId = UUID.fromString("f90515b5-437f-4427-abf6-e46afe1f0d54");
        var task = Task.builder()
                .id(taskId)
                .build();

        when(tasks.findOrThrow(taskId)).thenReturn(task);
        doThrow(TaskAccessException.class).when(assertions).assertLoggedUserCanAccessTask(task);

        // when-then
        assertThatThrownBy(() -> testee.deleteBy(taskId))
                .isInstanceOf(TaskAccessException.class);
    }
}