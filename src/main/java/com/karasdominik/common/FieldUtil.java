package com.karasdominik.common;

import lombok.NoArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;

import static io.micrometer.common.util.StringUtils.isBlank;
import static java.util.Objects.isNull;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class FieldUtil {

    public static void notNull(FieldInfo field, Object value) {
        if (isNull(value)) {
            throw new InvalidFieldException(String.format("%s is required", field.value()));
        }
    }

    public static void notBlank(FieldInfo field, String value) {
        if (isBlank(value)) {
            throw new InvalidFieldException(String.format("%s cannot be blank", field.value()));
        }
    }

    public static void maxLength(FieldInfo field, String content, int maxLength) {
        if (content.length() > maxLength) {
            throw new InvalidFieldException(String.format("%s can contain up to %d characters", field.value(), maxLength));
        }
    }

    public static void minLength(FieldInfo field, String content, int minLength) {
        if (content.length() < minLength) {
            throw new InvalidFieldException(String.format("%s must contain at least %d characters", field.value(), minLength));
        }
    }

    public static void isValid(boolean expression, FieldInfo field) {
        if (!expression) {
            throw new InvalidFieldException(String.format("%s is invalid", field.value()));
        }
    }

    public static void isValid(boolean expression, String errorMessage) {
        if (!expression) {
            throw new InvalidFieldException(errorMessage);
        }
    }

    public static boolean isEmail(String value) {
        return EmailValidator.getInstance().isValid(value);
    }
}
