package com.karasdominik.task.internal;

import com.karasdominik.common.LoggedUserProvider;
import com.karasdominik.common.LoggedUserProvider.LoggedUser;
import com.karasdominik.task.dto.exception.TaskAccessException;
import com.karasdominik.useraccount.internal.UserAccount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskAssertionsTest {

    @Mock
    private LoggedUserProvider loggedUserProvider;
    @InjectMocks
    private TaskAssertions testee;

    @Test
    void shouldThrowWhenLoggedUserIsNotTaskOwner() {
        // given
        var loggedUser = new LoggedUser(UUID.fromString("14795584-358e-4869-b6a8-a594656c53d3"));
        var task = Task.builder()
                .user(UserAccount.builder()
                        .id(UUID.fromString("8b14e9f0-7d61-4c0b-8a0b-86c49844f0ab"))
                        .build())
                .build();

        when(loggedUserProvider.getLoggedUser()).thenReturn(loggedUser);

        // when-then
        assertThatThrownBy(() -> testee.assertLoggedUserCanAccessTask(task))
                .isInstanceOf(TaskAccessException.class);
    }

}