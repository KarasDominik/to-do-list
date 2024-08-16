package com.karasdominik.useraccount;

import com.karasdominik.useraccount.UsersForTests.UserData;
import com.karasdominik.useraccount.internal.UserAccount;
import com.karasdominik.useraccount.internal.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static com.karasdominik.useraccount.UsersForTests.BOB;
import static com.karasdominik.useraccount.UsersForTests.SUPPORT;

@Component
@RequiredArgsConstructor
public class UserFixtures {

    private final UserAccountRepository users;
    private final PasswordEncoder passwordEncoder;

    public void setUp() {
        createUser(SUPPORT);
        createUser(BOB);
    }

    public void tearDown() {
        users.deleteAll();
    }

    private void createUser(UserData data) {
        var user = UserAccount.builder()
                .id(data.userId())
                .email(data.email().value())
                .password(passwordEncoder.encode(data.decodedPassword().value()))
                .build();
        users.save(user);
    }
}
