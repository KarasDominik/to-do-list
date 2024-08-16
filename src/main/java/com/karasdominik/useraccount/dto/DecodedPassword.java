package com.karasdominik.useraccount.dto;

import com.karasdominik.common.FieldInfo;

import java.util.Set;

import static com.karasdominik.common.FieldUtil.isValid;
import static com.karasdominik.common.FieldUtil.maxLength;
import static com.karasdominik.common.FieldUtil.minLength;
import static com.karasdominik.common.FieldUtil.notBlank;

public record DecodedPassword(String value) {

    public static DecodedPassword of(String password) {
        return new DecodedPassword(password);
    }

    private static final FieldInfo PASSWORD = new FieldInfo("Password");
    private static final Set<Character> SPECIAL_CHARACTERS = Set.of('!', '@', '#', '$', '%', '&', '*', '(', ')', '\'', '+', ',', '-', '.', '/', ':', ';', '<', '=', '>', '?', '[', ']', '^', '_', '`', '{', '|', '}');

    public DecodedPassword {
        notBlank(PASSWORD, value);
        maxLength(PASSWORD, value, 30);
        minLength(PASSWORD, value, 8);
        isValid(isValidPassword(value), PASSWORD);
    }

    private boolean isValidPassword(String value) {
        return containsUppercaseLetter(value) &&
                containsDigit(value) &&
                containsSpecialCharacter(value);
    }

    private boolean containsUppercaseLetter(String value) {
        return value.codePoints()
                .anyMatch(Character::isUpperCase);
    }

    private boolean containsDigit(String value) {
        return value.codePoints()
                .anyMatch(Character::isDigit);
    }

    private boolean containsSpecialCharacter(String value) {
        return value.codePoints()
                .mapToObj(c -> (char) c)
                .anyMatch(this::isSpecialCharacter);
    }

    private boolean isSpecialCharacter(char character) {
        return SPECIAL_CHARACTERS.contains(character);
    }
}
