package dev.midnightcoder.web;

import dev.midnightcoder.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String getSitemapPage(Model model) {
        var blogPaging = Pageable.ofSize(10).withPage(0);
        var blogs = blogService.fetchAllBlogs(blogPaging);

        model.addAttribute("blogs", blogs);
        return "blog-list";
    }

}
