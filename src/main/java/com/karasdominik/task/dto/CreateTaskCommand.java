package com.karasdominik.task.dto;

import com.karasdominik.common.FieldInfo;
import lombok.Builder;

import java.util.UUID;

import static com.karasdominik.common.FieldUtil.maxLength;
import static com.karasdominik.common.FieldUtil.notBlank;
import static com.karasdominik.common.FieldUtil.notNull;

@Builder
public record CreateTaskCommand(String content, UUID userId) {

    private static final FieldInfo CONTENT = new FieldInfo("Content");
    private static final FieldInfo USER_ID = new FieldInfo("userId");

    public CreateTaskCommand {
        notBlank(CONTENT, content);
        maxLength(CONTENT, content, 80);
        notNull(USER_ID, userId);
    }
}
