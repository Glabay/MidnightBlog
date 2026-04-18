package dev.midnightcoder.blog.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * @author Glabay | Glabay-Studios
 * @project MidnightBlog
 * @social Discord: Glabay
 * @since 2026-04-18
 */
@Getter
@Setter
public class BlogCreationRequest {
    private UUID id;
    private String blogName;
}
