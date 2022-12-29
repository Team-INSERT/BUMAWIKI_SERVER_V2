package com.project.bumawiki.global.jwt.util;

import com.project.bumawiki.domain.auth.domain.repository.AuthIdRepository;
import com.project.bumawiki.global.jwt.config.JwtProperties;
import com.project.bumawiki.global.jwt.exception.ExpiredJwtException;
import com.project.bumawiki.global.jwt.exception.InvalidJwtException;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static com.project.bumawiki.global.jwt.config.JwtConstants.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtUtil {

    private final JwtProperties jwtProperties;
    private final AuthIdRepository authIdRepository;

    public String resolveToken(HttpServletRequest request){
        String bearer = request.getHeader(jwtProperties.getHeader());

        return parseToken(bearer);
    }

    public String parseToken(String bearer){
        if(bearer != "" && bearer != null){
            String token = bearer.replaceAll(jwtProperties.getPrefix(), "").trim();
            checkingIfJwtExpired(token);
            return token;
        }
        return null;
    }

    public void checkingIfJwtExpired(String token){
        String authId = getJwt(token).getBody().get(AUTH_ID.getMessage()).toString();

        authIdRepository.findByAuthId(authId)
                .orElseThrow(() -> ExpiredJwtException.EXCEPTION);
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
