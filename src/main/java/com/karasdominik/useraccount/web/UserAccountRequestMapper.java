package com.karasdominik.useraccount.web;

import com.karasdominik.useraccount.dto.CreateUserAccountCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class UserAccountRequestMapper {

    private final PasswordEncoder passwordEncoder;

    CreateUserAccountCommand asCommand(CreateUserAccountRequest request) {
        return CreateUserAccountCommand.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .build();
    }
}
