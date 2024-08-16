package com.karasdominik.useraccount.web;

import java.util.UUID;

record CreateUserResponse(UUID userId) {

    static CreateUserResponse of(UUID uuid) {
        return new CreateUserResponse(uuid);
    }
}
