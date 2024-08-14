package com.karasdominik.common;

import com.karasdominik.security.AppUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class LoggedUserProvider {

    public LoggedUser getLoggedUser() {
        return asLoggedUser(getAuthentication());
    }

    private LoggedUser asLoggedUser(Authentication authentication) {
        return Optional.ofNullable(authentication.getPrincipal())
                .filter(AppUserDetails.class::isInstance)
                .map(principal -> LoggedUser.create((AppUserDetails) principal))
                .orElseThrow(() -> {
                    log.error("Trying to fetch not logged in user");
                    return new RuntimeException();
                });
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication();
    }

    public record LoggedUser(UUID id) {

        public static LoggedUser create(AppUserDetails principal) {
            return new LoggedUser(principal.userId());
        }
    }
}
