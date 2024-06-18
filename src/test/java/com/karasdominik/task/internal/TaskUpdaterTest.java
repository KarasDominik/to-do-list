package com.karasdominik.task.internal;

import com.karasdominik.task.dto.exception.TaskNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskUpdaterTest {

    @Mock
    private TaskRepository tasks;

    @InjectMocks
    private TaskUpdater testee;

    @Test
    void shouldThrowWhenTaskNotFound() {
        // given
        var id = UUID.randomUUID();
        when(tasks.findById(id)).thenReturn(Optional.empty());

        // when
        assertThatThrownBy(() -> testee.update(id))
                // then
                .isInstanceOf(TaskNotFoundException.class);
    }

}