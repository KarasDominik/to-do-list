package com.karasdominik.useraccount;

import com.karasdominik.useraccount.internal.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Component
@RequiredArgsConstructor
public class UserAssertions {

    private final UserAccountRepository users;
    private final PasswordEncoder passwordEncoder;

    public void assertUserCreated(UUID userId, Map<String, String> expectedContent) {
        assertThat(users.findById(userId))
                .isPresent()
                .hasValueSatisfying(task -> {
                    assertThat(task.email()).isEqualTo(expectedContent.get("email"));
                    assertThat(passwordEncoder.matches(expectedContent.get("password"), task.password())).isTrue();
                });
    }
}
