package com.karasdominik.useraccount.dto;

import com.karasdominik.common.FieldInfo;
import lombok.Builder;

import static com.karasdominik.common.FieldUtil.notNull;

@Builder
public record CreateUserAccountCommand(Email email, DecodedPassword password) {

    private static final FieldInfo EMAIL = new FieldInfo("Email");
    private static final FieldInfo PASSWORD = new FieldInfo("Password");

    public CreateUserAccountCommand {
        notNull(EMAIL, email);
        notNull(PASSWORD, password);
    }
}
