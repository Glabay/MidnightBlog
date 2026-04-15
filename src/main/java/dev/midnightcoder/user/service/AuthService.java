package dev.midnightcoder.user.service;

import dev.midnightcoder.user.dto.AuthRequest;
import dev.midnightcoder.user.dto.AuthResponse;
import dev.midnightcoder.user.dto.RegistrationRequest;
import dev.midnightcoder.user.dto.RegistrationResponse;
import dev.midnightcoder.user.model.User;
import dev.midnightcoder.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Glabay | Glabay-Studios
 * @project MidnightBlog
 * @social Discord: Glabay
 * @since 2026-04-13
 */
@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Nullable
    public AuthResponse authenticate(AuthRequest request) throws IllegalArgumentException {
        var user = userRepository.findByUsernameOrEmail(request.usernameOrEmail(), request.usernameOrEmail())
            .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!passwordEncoder.matches(request.password(), user.getPassword()))
            return new AuthResponse(
                null,
                user.getUsername(),
                user.getEmail(),
                "Invalid credentials"
            );

        return new AuthResponse(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            "Login Successful"
        );
    }

    public RegistrationResponse register(RegistrationRequest request) {
        if (userRepository.existsByEmail(request.email()))
            return new RegistrationResponse(
                null,
                request.username(),
                request.email(),
                "Email already exists"
            );

        if (userRepository.existsByUsername(request.username()))
            return new RegistrationResponse(
                null,
                request.username(),
                request.email(),
                "Username already exists"
            );

        if (request.password().length() < 6)
            return new RegistrationResponse(
                null,
                request.username(),
                request.email(),
                "Password must be at least 6 characters long"
            );
        if (!request.password().equals(request.confirmPassword()))
            return new RegistrationResponse(
                null,
                request.username(),
                request.email(),
                "Passwords do not match"
            );

        var user = new User();
            user.setEmail(request.email());
            user.setUsername(request.username());
            user.setPassword(passwordEncoder.encode(request.password()));

        var cachedUser = userRepository.save(user);

        return new RegistrationResponse(
            cachedUser.getId(),
            cachedUser.getUsername(),
            cachedUser.getEmail(),
            "Registration Successful"
        );
    }

}
