package dev.midnightcoder.comment.repository;

import dev.midnightcoder.comment.model.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * @author Glabay | Glabay-Studios
 * @project MidnightBlog
 * @social Discord: Glabay
 * @since 2026-04-13
 */
public interface CommentRepository extends JpaRepository<Comment, UUID> {
    List<Comment> findCommentByPostId(UUID postId);
    List<Comment> findCommentByPostId(UUID postId, Pageable pageable);
}
