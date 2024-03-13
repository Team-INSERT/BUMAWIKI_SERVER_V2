package com.project.bumawiki.global.jwt.auth;

import com.project.bumawiki.global.jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtAuth jwtAuth;
    private final JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtUtil.resolveToken(request);
        SetAuthenticationInSecurityContext(token);
        filterChain.doFilter(request, response);
    }

    private void SetAuthenticationInSecurityContext(String token) {
        if (token != null) {
            Authentication authentication = jwtAuth.authentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }
}
