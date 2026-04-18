package dev.midnightcoder.comment.controller;

import dev.midnightcoder.comment.dto.CommentCreationRequest;
import dev.midnightcoder.comment.model.Comment;
import dev.midnightcoder.comment.service.CommentService;
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
@RequestMapping("/api/v1/comments")
public class CommentApiController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody CommentCreationRequest request) {
        var createdComment = commentService.createComment(request);
        return ResponseEntity.ok(createdComment);
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getCommentsByPostId(@RequestParam UUID postId,
                                                             @RequestParam(defaultValue = "0") int pageId,
                                                             @RequestParam(defaultValue = "10") int size) {
        var pageable = PageRequest.of(pageId, size);
        var comments = commentService.fetchCommentsByPostId(postId, pageable);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable UUID postId) {
        var comments = commentService.fetchCommentsByPostId(postId);
        return ResponseEntity.ok(comments);
    }
}
