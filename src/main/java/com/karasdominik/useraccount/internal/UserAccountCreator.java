package com.karasdominik.useraccount.internal;

import com.karasdominik.useraccount.dto.CreateUserAccountCommand;
import com.karasdominik.useraccount.dto.exception.EmailAlreadyUsedException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
class UserAccountCreator {

    private final UserAccountRepository users;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UUID create(CreateUserAccountCommand command) {
        log.info("Creating new user: {}", command.email());
        assertEmailNotUsed(command.email().value());
        var user = UserAccount.create(command, passwordEncoder::encode);
        users.save(user);
        log.info("User {} created", user.id());
        return user.id();
    }

    private void assertEmailNotUsed(String email) {
        if (users.existsByEmail(email)) {
            log.warn("User {} could not be created because email is already taken", email);
            throw new EmailAlreadyUsedException(email);
        }
    }
}
