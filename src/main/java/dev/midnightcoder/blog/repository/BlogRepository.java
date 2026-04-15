package dev.midnightcoder.blog.repository;

import dev.midnightcoder.blog.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Glabay | Glabay-Studios
 * @project MidnightBlog
 * @social Discord: Glabay
 * @since 2026-04-13
 */
@Repository
public interface BlogRepository extends JpaRepository<Blog, UUID> {
}
