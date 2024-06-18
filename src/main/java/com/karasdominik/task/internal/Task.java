package com.karasdominik.task.internal;

import com.karasdominik.task.dto.CreateTaskCommand;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

import static java.util.UUID.randomUUID;

@Entity
@SuperBuilder
@NoArgsConstructor
@Getter
public class Task {

    public static Task create(CreateTaskCommand command) {
        return Task.builder()
                .id(randomUUID())
                .content(command.content())
                .done(false)
                .build();
    }

    @Id
    private UUID id;
    private String content;
    private Boolean done;

    public void update() {
        this.done = !done();
    }
}
