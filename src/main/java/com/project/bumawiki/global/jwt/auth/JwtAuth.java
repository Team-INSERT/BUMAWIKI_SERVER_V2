package com.project.bumawiki.global.jwt.auth;

import com.project.bumawiki.global.jwt.config.JwtConstants;
import com.project.bumawiki.global.jwt.config.JwtProperties;
import com.project.bumawiki.global.jwt.exception.InvalidJwtException;
import com.project.bumawiki.global.jwt.util.JwtUtil;
import com.project.bumawiki.global.security.auth.AuthDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtAuth {

    private final JwtUtil jwtUtil;
    private final AuthDetailsService authDetailsService;

    public Authentication authentication(String token){
        Claims claims = jwtUtil.getJwtBody(token);

        if(isNotAccessToken(token)){
            throw InvalidJwtException.EXCEPTION;
        }

        UserDetails userDetails = authDetailsService.loadUserByUsername(claims.get(JwtConstants.AUTH_ID.message).toString());
        return new UsernamePasswordAuthenticationToken(userDetails,"", userDetails.getAuthorities());
    }

    private boolean isNotAccessToken(String token) {
        if(token.isEmpty()){
            throw InvalidJwtException.EXCEPTION;
        }
        String role = jwtUtil.getHeader(token).get(JwtConstants.ROLE).toString();
        if(role.equals(JwtConstants.ACCESS_KEY.message)){
            return false;
        }
        return true;
    }



}
