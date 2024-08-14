package com.karasdominik.useraccount.internal;

import com.karasdominik.useraccount.dto.CreateUserAccountCommand;
import com.karasdominik.useraccount.dto.Email;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;
import java.util.function.Function;

@Entity
@SuperBuilder
@NoArgsConstructor
public class UserAccount {

    public static UserAccount create(CreateUserAccountCommand command, Function<String, String> passwordEncoder) {
        return UserAccount.builder()
                .id(UUID.randomUUID())
                .email(command.email().value())
                .password(passwordEncoder.apply(command.password().value()))
                .build();
    }

    @Id
    @Getter
    private UUID id;

    private String email;

    @Getter
    private String password;

    public Email email() {
        return Email.of(email);
    }
}
