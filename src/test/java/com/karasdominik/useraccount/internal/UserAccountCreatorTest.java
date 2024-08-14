package com.karasdominik.useraccount.internal;

import com.karasdominik.useraccount.dto.CreateUserAccountCommand;
import com.karasdominik.useraccount.dto.DecodedPassword;
import com.karasdominik.useraccount.dto.Email;
import com.karasdominik.useraccount.dto.exception.EmailAlreadyUsedException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserAccountCreatorTest {

    @Mock
    private UserAccountRepository users;

    @InjectMocks
    private UserAccountCreator testee;

    @Test
    void shouldNotCreateUserWhenEmailAlreadyUsed() {
        // given
        var email = Email.of("test@gmail.com");
        when(users.existsByEmail(email.value())).thenReturn(true);
        var command = CreateUserAccountCommand.builder()
                .email(email)
                .password(new DecodedPassword("Password123!"))
                .build();

        // when
        assertThatThrownBy(() -> testee.create(command))
                // then
                .isInstanceOf(EmailAlreadyUsedException.class);

        then(users).should(never()).save(any());
    }

}