package dev.midnightcoder.user.dto;

/**
 * @author Glabay | Glabay-Studios
 * @project MidnightBlog
 * @social Discord: Glabay
 * @since 2026-04-13
 */
public record RegistrationRequest(
    String username,
    String email,
    String password,
    String confirmPassword
) {}
