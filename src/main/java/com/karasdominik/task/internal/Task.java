package com.karasdominik.task.internal;

import com.karasdominik.task.dto.CreateTaskCommand;
import com.karasdominik.useraccount.internal.UserAccount;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.util.UUID.randomUUID;

@SuperBuilder
@NoArgsConstructor
@Getter
public class Task {

    public static Task create(CreateTaskCommand command,
                              Function<UUID, UserAccount> userResolver,
                              Supplier<Instant> now) {
        return Task.builder()
                .id(randomUUID())
                .content(command.content())
                .done(false)
                .user(userResolver.apply(command.userId()))
                .createdDate(now.get())
                .build();
    }

    @Id
    private UUID id;
    private String content;
    private Boolean done;
    private Instant createdDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private UserAccount user;

    public void update() {
        this.done = !done();
    }
}
