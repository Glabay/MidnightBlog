package dev.midnightcoder.web;

import dev.midnightcoder.blog.service.BlogService;
import dev.midnightcoder.comment.service.CommentService;
import dev.midnightcoder.post.service.PostService;
import dev.midnightcoder.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;
import java.util.UUID;

/**
 * @author Glabay | Glabay-Studios
 * @project MidnightBlog
 * @social Discord: Glabay
 * @since 2026-04-13
 */
@Controller
@RequestMapping
@RequiredArgsConstructor
public class SitemapController {
    private final BlogService blogService;
    private final PostService postService;
    private final CommentService commentService;
    private final UserService userService;

    @GetMapping
    public String getHomePage() {
        return "index";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterPage() {
        return "register";
    }

    @GetMapping("/blogs")
    public String getSitemapPage(Authentication authentication, Model model) {
        var blogPaging = Pageable.ofSize(10).withPage(0);
        var blogs = blogService.fetchAllBlogs(blogPaging);

        if (authentication != null) {
            var usernameOrEmail = authentication.getName();
            var user = userService.fetchUserByUsernameOrEmail(usernameOrEmail);
            var cachedBlog = blogService.fetchBlogByAuthor(user.getId());

            model.addAttribute("hasBlog", Objects.nonNull(cachedBlog));
            model.addAttribute("userId", user.getId());
        }

        model.addAttribute("blogs", blogs);
        return "blog-list";
    }

    @GetMapping("/blog/{blogId}")
    public String getBlogForId(@PathVariable UUID blogId,
                               Authentication authentication,
                               Model model) {
        var blog = blogService.fetchBlogById(blogId);
        var posts = postService.fetchPostByBlog(blogId);
        if (authentication != null) {
            var usernameOrEmail = authentication.getName();
            var user = userService.fetchUserByUsernameOrEmail(usernameOrEmail);
            model.addAttribute("userId", user.getId());
            model.addAttribute("username", user.getUsername());

            var isOwner = blog.getAuthor().getId().equals(user.getId());
            model.addAttribute("isOwner", isOwner);
        }

        model.addAttribute("blog", blog);
        model.addAttribute("posts", posts);
        return "blog";
    }


    @GetMapping("/blog/post/{postId}")
    public String getBlogPostForId(@PathVariable UUID postId,
                               Authentication authentication,
                               Model model) {
        var post = postService.fetchPostById(postId);
        if (authentication != null) {
            var usernameOrEmail = authentication.getName();
            var user = userService.fetchUserByUsernameOrEmail(usernameOrEmail);
            model.addAttribute("userId", user.getId());
            model.addAttribute("username", user.getUsername());

            var isOwner = post.getBlog().getAuthor().getId().equals(user.getId());
            model.addAttribute("isOwner", isOwner);
        }
        var comments = commentService.fetchCommentsByPostId(postId);

        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        return "blog-post";
    }

}
