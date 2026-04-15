package dev.midnightcoder.blog.model;

import dev.midnightcoder.post.model.Post;
import dev.midnightcoder.user.model.User;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
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
@Table(name = "blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private String content;

    @OneToOne
    private User author;

    @OneToMany(mappedBy = "blog")
    private List<Post> posts;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @PostConstruct
    public void init() {
        if (this.id != null) return;
        this.id = UUID.randomUUID();
    }

    @PreUpdate
    private void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @PrePersist
    private void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
}
