package com.karasdominik.security;

import com.karasdominik.useraccount.internal.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final UserAccountRepository users;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = users.findByEmail(email);
        return user.map(u -> User.builder()
                        .username(u.email().value())
                        .password(u.password())
                        .build()
                )
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }
}
