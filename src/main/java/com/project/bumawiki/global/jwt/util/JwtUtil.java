package com.project.bumawiki.global.jwt.util;

import com.project.bumawiki.global.jwt.config.JwtProperties;
import com.project.bumawiki.global.jwt.exception.InvalidJwtException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
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

        return parseToken(bearer);
    }

    public String parseToken(String bearer){
        if(bearer != null){
            return  bearer.replaceAll(jwtProperties.getPrefix(), "").trim();
        }
        return null;
    }

    public Jws<Claims> getJwt(String token){
        if(token == null){
            throw InvalidJwtException.EXCEPTION;
        }
        return Jwts.parser().setSigningKey(jwtProperties.getSecret()).parseClaimsJws(token);
    }



    public Claims getJwtBody(String bearer){
        Jws<Claims> jwt = getJwt(parseToken(bearer));
        return jwt.getBody();
    }

    public JwsHeader getHeader(String bearer) {
        Jws<Claims> jwt = getJwt(parseToken(bearer));
        return jwt.getHeader();
    }
}
