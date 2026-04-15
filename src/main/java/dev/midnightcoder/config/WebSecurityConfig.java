package dev.midnightcoder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Glabay | Glabay-Studios
 * @project MidnightBlog
 * @social Discord: Glabay
 * @since 2026-04-13
 */
@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {
        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(
                auth ->
                    auth
                        .requestMatchers(
                            "/",
                            "/login",
                            "/register",
                            "/api/auth/**"
                        ).permitAll()
                        .requestMatchers(HttpMethod.GET,
                            "/blogs/**",
                            // Resources that should be allowed
                            "/css/**",
                            "/js/**",
                            "/img/**",
                            "/favicon.ico"
                        ).permitAll()
                        .anyRequest().authenticated()
            )
            .formLogin(login ->
                login.loginPage("/login")
                    .defaultSuccessUrl("/", true)
                    .permitAll())
            .logout(logout ->
                logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                    .permitAll());
        return http.build();
    }
}
