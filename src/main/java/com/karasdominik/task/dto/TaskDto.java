package com.karasdominik.task.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record TaskDto(UUID taskId, String content, Boolean done) {
}
