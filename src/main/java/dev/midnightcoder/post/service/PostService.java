package dev.midnightcoder.post.service;

import dev.midnightcoder.blog.service.BlogService;
import dev.midnightcoder.post.dto.PostCreationRequest;
import dev.midnightcoder.post.model.Post;
import dev.midnightcoder.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author Glabay | Glabay-Studios
 * @project MidnightBlog
 * @social Discord: Glabay
 * @since 2026-04-18
 */
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final BlogService blogService;

    public Post createPost(PostCreationRequest request) {
        var blog = blogService.fetchBlogById(request.getBlogId());
        var post = new Post();
            post.setBlog(blog);
            post.setTitle(request.getTitle());
            post.setContent(request.getContent());

        return postRepository.save(post);
    }

    public List<Post> fetchPostByBlog(UUID blogId) {
        return postRepository.findByBlogId(blogId);
    }

    public List<Post> fetchPostByBlog(UUID blogId, Pageable pageable) {
        return postRepository.findByBlogId(blogId, pageable);
    }

    public List<Post> fetchAllPostsFromBlog(UUID blogId) {
        var blog = blogService.fetchBlogById(blogId);
        return postRepository.findAllByBlog(blog);
    }

    public Post fetchPostById(UUID postId) {
        return postRepository.findById(postId).orElseThrow(() ->
            new IllegalArgumentException("Post not found with ID: " + postId));
    }
}
