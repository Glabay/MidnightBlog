package dev.midnightcoder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;

import static org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter.Directive.COOKIES;

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
            .formLogin(login -> login
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/", true)
                .usernameParameter("email")
                .passwordParameter("password")
            )
            .logout((logout) ->
                logout.addLogoutHandler(new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter(COOKIES))))
            .headers(configurer ->
                configurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        return http.build();
    }
}
