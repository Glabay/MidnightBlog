package dev.midnightcoder.user.dto;

import java.util.UUID;

/**
 * @author Glabay | Glabay-Studios
 * @project MidnightBlog
 * @social Discord: Glabay
 * @since 2026-04-13
 */
public record AuthResponse(
    UUID userId,
    String username,
    String email,
    String message
) {}
