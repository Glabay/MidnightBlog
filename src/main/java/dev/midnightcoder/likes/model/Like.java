package dev.midnightcoder.likes.model;

import dev.midnightcoder.comment.model.Comment;
import dev.midnightcoder.post.model.Post;
import dev.midnightcoder.user.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * @author Glabay | Glabay-Studios
 * @project MidnightBlog
 * @social Discord: Glabay
 * @since 2026-04-13
 */
@Getter
@Setter
@Entity
@Table(name = "likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    private User user;

    @OneToOne
    private Post post;

    @OneToOne
    private Comment comment;


}
