package com.karasdominik.useraccount.internal;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserAccountRepository extends JpaRepository<UserAccount, UUID> {

    boolean existsByEmail(String email);

    Optional<UserAccount> findByEmail(String email);
}