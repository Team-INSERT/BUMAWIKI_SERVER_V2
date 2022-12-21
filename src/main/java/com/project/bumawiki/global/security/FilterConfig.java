package com.project.bumawiki.global.security;

import com.project.bumawiki.domain.auth.domain.repository.AuthIdRepository;
import com.project.bumawiki.global.error.ExceptionFilter;
import com.project.bumawiki.global.jwt.auth.JwtAuth;
import com.project.bumawiki.global.jwt.auth.JwtFilter;
import com.project.bumawiki.global.jwt.util.JwtProvider;
import com.project.bumawiki.global.jwt.util.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class FilterConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final JwtUtil jwtUtil;
    private final JwtAuth jwtAuth;

    @Override
    public void configure(HttpSecurity builder) throws Exception {
        JwtFilter jwtFilter = new JwtFilter(jwtAuth, jwtUtil);
        ExceptionFilter globalExceptionFilter = new ExceptionFilter();
        builder.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        builder.addFilterBefore(globalExceptionFilter, JwtFilter.class);
    }
}
