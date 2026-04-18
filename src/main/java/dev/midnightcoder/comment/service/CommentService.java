package dev.midnightcoder.comment.service;

import dev.midnightcoder.comment.dto.CommentCreationRequest;
import dev.midnightcoder.comment.model.Comment;
import dev.midnightcoder.comment.repository.CommentRepository;
import dev.midnightcoder.post.service.PostService;
import dev.midnightcoder.user.service.UserService;
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
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostService postService;
    private final UserService userService;

    public Comment createComment(CommentCreationRequest request) {
        var author = userService.getUserByUserId(request.getUserId());
        var post = postService.fetchPostById(request.getPostId());
        var comment = new Comment();
            comment.setAuthor(author);
            comment.setPost(post);
            comment.setContent(request.getContent());
        return commentRepository.save(comment);
    }

    public List<Comment> fetchCommentsByPostId(UUID postId) {
        return commentRepository.findCommentByPostId(postId);
    }

    public List<Comment> fetchCommentsByPostId(UUID postId, Pageable pageable) {
        return commentRepository.findCommentByPostId(postId, pageable);
    }
}
