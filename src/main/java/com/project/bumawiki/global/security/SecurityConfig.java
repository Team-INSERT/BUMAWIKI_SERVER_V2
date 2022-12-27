package com.project.bumawiki.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.bumawiki.global.error.CustomAuthenticationEntryPoint;
import com.project.bumawiki.global.jwt.auth.JwtAuth;
import com.project.bumawiki.global.jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsUtils;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private static final String USER = "USER";

    private final ObjectMapper objectMapper;
    private final JwtUtil jwtUtil;
    private final JwtAuth jwtAuth;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin().disable()
                .cors()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers(HttpMethod.POST, "/docs/api/**").hasRole(USER)
                .anyRequest().permitAll()
                .and()
                .exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint(objectMapper))
                .and()
                .apply(new FilterConfig(jwtUtil, jwtAuth));

        return http.build();
    }
}
