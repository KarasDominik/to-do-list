package com.karasdominik.useraccount.internal;

import com.karasdominik.useraccount.dto.CreateUserAccountCommand;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Entity
@SuperBuilder
@NoArgsConstructor
@Getter
public class UserAccount {

    public static UserAccount create(CreateUserAccountCommand command) {
        return UserAccount.builder()
                .id(UUID.randomUUID())
                .email(command.email())
                .password(command.password())
                .build();
    }

    @Id
    private UUID id;

    private String email;
    private String password;
}
