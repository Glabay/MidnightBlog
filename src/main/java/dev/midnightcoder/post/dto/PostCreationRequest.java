package dev.midnightcoder.post.dto;

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
public class PostCreationRequest {
    private UUID blogId;
    private String title;
    private String content;
}
