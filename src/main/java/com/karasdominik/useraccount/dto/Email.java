package com.karasdominik.useraccount.dto;

import com.karasdominik.common.FieldInfo;

import static com.karasdominik.common.FieldUtil.isEmail;
import static com.karasdominik.common.FieldUtil.isValid;
import static com.karasdominik.common.FieldUtil.maxLength;
import static com.karasdominik.common.FieldUtil.notBlank;

public record Email(String value) {

    public static Email of(String email) {
        return new Email(email);
    }

    private static final FieldInfo EMAIL = new FieldInfo("Email");

    public Email {
        notBlank(EMAIL, value);
        maxLength(EMAIL, value, 50);
        isValid(isEmail(value), EMAIL);
        value = value.toLowerCase();

    }

    @Override
    public String toString() {
        return value;
    }
}
