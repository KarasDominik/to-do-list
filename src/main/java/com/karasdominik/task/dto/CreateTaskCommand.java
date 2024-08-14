package com.karasdominik.task.dto;

import com.karasdominik.common.FieldInfo;
import lombok.Builder;

import static com.karasdominik.common.FieldUtil.maxLength;
import static com.karasdominik.common.FieldUtil.notBlank;

@Builder
public record CreateTaskCommand(String content) {

    private static final FieldInfo CONTENT = new FieldInfo("Content");

    public CreateTaskCommand {
        notBlank(CONTENT, content);
        maxLength(CONTENT, content, 80);
    }
}
