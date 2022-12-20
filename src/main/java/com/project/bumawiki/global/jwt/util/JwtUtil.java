package com.project.bumawiki.global.jwt.util;

import com.project.bumawiki.global.jwt.config.JwtProperties;
import com.project.bumawiki.global.jwt.exception.InvalidJwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtUtil {

    private final JwtProperties jwtProperties;

    public String resolveToken(HttpServletRequest request){
        String bearer = request.getHeader(jwtProperties.getHeader());
        if(bearer.isEmpty()){
            throw InvalidJwtException.EXCEPTION;
        }

        return parseToken(bearer);
    }

    public String parseToken(String bearer){
        if(!bearer.startsWith(jwtProperties.getPrefix())){
            throw InvalidJwtException.EXCEPTION;
        }

        return  bearer.replaceAll(jwtProperties.getPrefix(), "").trim();
    }

    public Jws<Claims> getJwt(String bearer){
        String token = parseToken(bearer);
        return Jwts.parser().setSigningKey(jwtProperties.getSecret()).parseClaimsJws(token);
    }

    public Claims getBody(String bearer){
        Jws<Claims> jwt = getJwt(bearer);
        return jwt.getBody();
    }
}
