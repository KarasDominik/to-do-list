package com.karasdominik.useraccount.web;

import com.karasdominik.useraccount.dto.CreateUserAccountCommand;
import com.karasdominik.useraccount.dto.DecodedPassword;
import com.karasdominik.useraccount.dto.Email;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
class UserAccountRequestMapper {


    static CreateUserAccountCommand asCommand(CreateUserAccountRequest request) {
        return CreateUserAccountCommand.builder()
                .email(Email.of(request.email()))
                .password(DecodedPassword.of(request.password()))
                .build();
    }
}
