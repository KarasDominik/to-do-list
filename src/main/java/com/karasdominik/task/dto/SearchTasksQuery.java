package com.karasdominik.task.dto;

import com.karasdominik.common.FieldInfo;
import lombok.Builder;

import java.util.UUID;

import static com.karasdominik.common.FieldUtil.notNull;

@Builder
public record SearchTasksQuery(UUID userId) {

    private static final FieldInfo USER_ID = new FieldInfo("userId");

    public SearchTasksQuery {
        notNull(USER_ID, userId);
    }
}
