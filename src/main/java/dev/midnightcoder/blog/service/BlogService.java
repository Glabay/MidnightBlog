package dev.midnightcoder.blog.service;

import dev.midnightcoder.blog.dto.BlogCreationRequest;
import dev.midnightcoder.blog.model.Blog;
import dev.midnightcoder.blog.repository.BlogRepository;
import dev.midnightcoder.user.model.User;
import dev.midnightcoder.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Glabay | Glabay-Studios
 * @project MidnightBlog
 * @social Discord: Glabay
 * @since 2026-04-18
 */
@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final UserService userService;

    public Blog createBlog(BlogCreationRequest request) {
        var user = userService.getUserByUserId(request.getId());
        var blog = new Blog();
            blog.setAuthor(user);
            blog.setTitle(request.getBlogName());

        return blogRepository.save(blog);
    }

    public Blog fetchBlogByAuthor(UUID authorId) {
        var author = userService.getUserByUserId(authorId);
        var blog = blogRepository.findByAuthor(author);
        return blog.orElse(null);

    }

    public Blog fetchBlogById(UUID blogId) {
        return blogRepository.findById(blogId).orElseThrow(() ->
            new IllegalArgumentException("Blog not found with ID: " + blogId));
    }

    public List<Blog> fetchAllBlogs() {
        return blogRepository.findAll();
    }

    public List<Blog> fetchAllBlogs(Pageable pageable) {
        return blogRepository.findAll(pageable).getContent();
    }
}
