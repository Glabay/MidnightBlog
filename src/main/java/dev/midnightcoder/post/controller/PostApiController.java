package dev.midnightcoder.post.controller;

import dev.midnightcoder.post.dto.PostCreationRequest;
import dev.midnightcoder.post.model.Post;
import dev.midnightcoder.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("/api/v1/posts")
public class PostApiController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostCreationRequest request) {
        var createdPost = postService.createPost(request);
        return ResponseEntity.ok(createdPost);
    }

    @GetMapping
    public ResponseEntity<List<Post>> fetchAllPosts(@RequestParam UUID blogId,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size) {
        var posts = postService.fetchPostByBlog(blogId, Pageable.ofSize(size).withPage(page));
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{blogId}/blog")
    public ResponseEntity<List<Post>> fetchAllPostsFromBlog(@PathVariable UUID blogId) {
        var posts = postService.fetchAllPostsFromBlog(blogId);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{postId}/post")
    public ResponseEntity<Post> fetchPostById(@PathVariable UUID postId) {
        var post = postService.fetchPostById(postId);
        return ResponseEntity.ok(post);
    }
}
