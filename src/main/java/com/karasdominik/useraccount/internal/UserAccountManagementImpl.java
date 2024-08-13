package com.karasdominik.useraccount.internal;

import com.karasdominik.useraccount.UserAccountManagement;
import com.karasdominik.useraccount.dto.CreateUserAccountCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
class UserAccountManagementImpl implements UserAccountManagement {

    private final UserAccountCreator creator;

    @Override
    public UUID create(CreateUserAccountCommand command) {
        return creator.create(command);
    }
}
