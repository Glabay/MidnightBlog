package dev.midnightcoder.web;

import org.springframework.stereotype.Controller;
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
public class SitemapController {

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
}
