package dev.midnightcoder.user.controller;

import dev.midnightcoder.user.dto.AuthRequest;
import dev.midnightcoder.user.dto.RegistrationRequest;
import dev.midnightcoder.user.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Glabay | Glabay-Studios
 * @project MidnightBlog
 * @social Discord: Glabay
 * @since 2026-04-13
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public String authenticate(@ModelAttribute AuthRequest request) {
        var authenticatedUser = authService.authenticate(request);
        if (authenticatedUser != null && authenticatedUser.userId() == null)
            return "redirect:/login?error=".concat(authenticatedUser.message());

        return "redirect:/";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegistrationRequest request) {
        var registeredUser = authService.register(request);
        if (registeredUser != null && registeredUser.userId() == null)
            return "redirect:/register?error=".concat(registeredUser.message());

        return "redirect:/login?registration=success";
    }
}
