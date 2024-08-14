package com.karasdominik.useraccount.dto;

import com.karasdominik.common.FieldInfo;

import java.util.regex.Pattern;

import static com.karasdominik.common.FieldUtil.isValid;
import static com.karasdominik.common.FieldUtil.maxLength;
import static com.karasdominik.common.FieldUtil.minLength;
import static com.karasdominik.common.FieldUtil.notBlank;

public record DecodedPassword(String value) {

    public static DecodedPassword of(String password) {
        return new DecodedPassword(password);
    }

    private static final FieldInfo PASSWORD = new FieldInfo("Password");
    private static final Pattern VALID_PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*(),.?\":{}|<>]).*$");

    public DecodedPassword {
        notBlank(PASSWORD, value);
        maxLength(PASSWORD, value, 30);
        minLength(PASSWORD, value, 8);
        isValid(isValidPassword(value), PASSWORD);
    }

    private boolean isValidPassword(String value) {
        return VALID_PASSWORD_PATTERN.matcher(value).matches();
    }
}
