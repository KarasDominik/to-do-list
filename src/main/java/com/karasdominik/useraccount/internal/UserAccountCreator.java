package com.karasdominik.useraccount.internal;

import com.karasdominik.useraccount.dto.CreateUserAccountCommand;
import com.karasdominik.useraccount.dto.exception.EmailAlreadyUsedException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
class UserAccountCreator {

    private final UserAccountRepository users;

    @Transactional
    public UUID create(CreateUserAccountCommand command) {
        log.info("Creating new user: {}", command.email());
        assertEmailNotUsed(command.email());
        var user = UserAccount.create(command);
        users.save(user);
        log.info("User {} created", user.id());
        return user.id();
    }

    private void assertEmailNotUsed(String email) {
        if (users.existsByEmail(email)) {
            throw new EmailAlreadyUsedException(email);
        }
    }
}
