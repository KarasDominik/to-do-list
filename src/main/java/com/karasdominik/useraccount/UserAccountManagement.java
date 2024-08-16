package com.karasdominik.useraccount;

import com.karasdominik.useraccount.dto.CreateUserAccountCommand;

import java.util.UUID;

public interface UserAccountManagement {

    UUID create(CreateUserAccountCommand command);
}
