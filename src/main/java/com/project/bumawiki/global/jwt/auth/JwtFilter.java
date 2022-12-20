package com.project.bumawiki.global.jwt.auth;

import com.project.bumawiki.domain.auth.domain.repository.AuthIdRepository;
import com.project.bumawiki.domain.user.exception.UserNotLoginException;
import com.project.bumawiki.global.jwt.config.JwtConstants;
import com.project.bumawiki.global.jwt.exception.InvalidJwtException;
import com.project.bumawiki.global.jwt.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtAuth jwtAuth;
    private final JwtUtil jwtUtil;
    private final AuthIdRepository authIdRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtUtil.resolveToken(request);
        checkLoginStatus(token);
        SetAuthenticationInSecurityContext(token);
        filterChain.doFilter(request, response);
    }

    private void checkLoginStatus(String token){
        Claims body = jwtUtil.getJwt(token).getBody();

        if(body.isEmpty()){
            throw InvalidJwtException.EXCEPTION;
        }
        String authId = body.get(JwtConstants.AUTH_ID.message).toString();

        authIdRepository.findByAuthId(authId)
                .orElseThrow(() -> UserNotLoginException.EXCEPTION);
    }

    private void SetAuthenticationInSecurityContext(String token){
        Authentication authentication = jwtAuth.authentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
