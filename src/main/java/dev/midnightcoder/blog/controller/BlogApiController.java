package dev.midnightcoder.blog.controller;

import dev.midnightcoder.blog.dto.BlogCreationRequest;
import dev.midnightcoder.blog.model.Blog;
import dev.midnightcoder.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author Glabay | Glabay-Studios
 * @project MidnightBlog
 * @social Discord: Glabay
 * @since 2026-04-18
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/blogs")
public class BlogApiController {
    public final BlogService blogService;

    @PostMapping
    public ResponseEntity<Blog> createBlog(@RequestBody BlogCreationRequest request) {
        var createdBlog = blogService.createBlog(request);
        return ResponseEntity.ok(createdBlog);
    }

    @GetMapping
    public ResponseEntity<List<Blog>> fetchAllBlogs(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size) {
        var blogs = blogService.fetchAllBlogs(PageRequest.of(page, size));
        return ResponseEntity.ok(blogs);
    }

    @GetMapping("/{authorId}/author")
    public ResponseEntity<Blog> fetchBlogByAuthor(@PathVariable UUID authorId) {
        var blog = blogService.fetchBlogByAuthor(authorId);
        return ResponseEntity.ok(blog);
    }

    @GetMapping("/{blogId}/blog")
    public ResponseEntity<Blog> fetchBlogById(@PathVariable UUID blogId) {
        var blog = blogService.fetchBlogById(blogId);
        return ResponseEntity.ok(blog);
    }
}
