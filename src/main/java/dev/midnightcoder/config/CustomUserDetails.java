package dev.midnightcoder.config;

import dev.midnightcoder.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NullMarked;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Glabay | Glabay-Studios
 * @project MidnightBlog
 * @social Discord: Glabay
 * @since 2026-04-23
 */
@Service
@NullMarked
@RequiredArgsConstructor
public class CustomUserDetails implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(CustomUserDetails.class);
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("Loading user by username: {}", email);
        var user = userService.fetchUserByUsernameOrEmail(email);

        return User.withUsername(user.getUsername())
            .password(user.getPassword())
            .roles("USER")
            .build();
    }
}
