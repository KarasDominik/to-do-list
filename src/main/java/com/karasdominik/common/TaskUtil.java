package com.karasdominik.common;

import com.karasdominik.task.dto.exception.InvalidContentException;
import lombok.experimental.UtilityClass;

import static io.micrometer.common.util.StringUtils.isNotBlank;

@UtilityClass
public class TaskUtil {

    private static final int MAX_CONTENT_LENGTH = 50;

    public static void notBlank(String content) {
        if (!isNotBlank(content)) {
            throw new InvalidContentException("Content cannot be blank");
        }
    }

    public static void maxLength(String content) {
        if (content.length() > MAX_CONTENT_LENGTH) {
            throw new InvalidContentException(String.format("Content exceeds maximum length: %d", MAX_CONTENT_LENGTH));
        }
    }

}
