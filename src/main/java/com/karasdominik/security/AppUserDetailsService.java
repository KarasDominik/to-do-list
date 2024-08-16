package com.karasdominik.security;

import com.karasdominik.useraccount.internal.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final UserAccountRepository users;

    @Override
    public AppUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = users.findByEmail(email);
        return user
                .map(u -> new AppUserDetails(u.id(), u.email().value(), u.password(), emptyList()))
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }
}
