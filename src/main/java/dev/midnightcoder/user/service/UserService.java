package dev.midnightcoder.user.service;

import dev.midnightcoder.user.model.User;
import dev.midnightcoder.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Glabay | Glabay-Studios
 * @project MidnightBlog
 * @social Discord: Glabay
 * @since 2026-04-18
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUserByUserId(UUID userId) {
        return userRepository.findById(userId)
            .orElseThrow(() ->
                new IllegalArgumentException("User not found with ID: " + userId));
    }
}
