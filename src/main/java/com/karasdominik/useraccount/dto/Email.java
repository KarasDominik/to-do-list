package com.karasdominik.useraccount.dto;

import com.karasdominik.common.FieldInfo;

import java.util.regex.Pattern;

import static com.karasdominik.common.FieldUtil.isValid;
import static com.karasdominik.common.FieldUtil.maxLength;
import static com.karasdominik.common.FieldUtil.notBlank;

public record Email(String value) {

    public static Email of(String email) {
        return new Email(email);
    }

    private static final FieldInfo EMAIL = new FieldInfo("Email");
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public Email {
        notBlank(EMAIL, value);
        maxLength(EMAIL, value, 50);
        isValid(isEmail(value), EMAIL);
        value = value.toLowerCase();
    }

    private boolean isEmail(String value) {
        return VALID_EMAIL_ADDRESS_REGEX.matcher(value).matches();
    }
}
