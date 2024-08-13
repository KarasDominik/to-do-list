package com.karasdominik.useraccount.dto;

import lombok.Builder;

@Builder
public record CreateUserAccountCommand(String email, String password) {
}
