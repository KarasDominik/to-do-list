package com.karasdominik.useraccount;

import com.karasdominik.useraccount.dto.DecodedPassword;
import com.karasdominik.useraccount.dto.Email;
import lombok.Builder;

import java.util.UUID;

public class UsersForTests {

    public static final UUID SUPPORT_ID = UUID.fromString("14d1a490-2bf4-43d6-aa76-f0e36143ecba");
    public static final UserData SUPPORT = new UserData(SUPPORT_ID, Email.of("support@gmail.com"), DecodedPassword.of("testPassword123!"));

    public static final UUID BOB_ID = UUID.fromString("3067c94c-cb64-4c6e-96b2-1108e5cab765");
    public static final UserData BOB = new UserData(BOB_ID, Email.of("bob@wp.pl"), DecodedPassword.of("Password123!"));

    @Builder
    public record UserData(UUID userId, Email email, DecodedPassword decodedPassword) {}
}
