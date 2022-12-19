package com.project.bumawiki.global.security.jwt;

import com.project.bumawiki.auth.domain.RefreshToken;
import com.project.bumawiki.auth.domain.repository.RefreshTokenRepository;
import com.project.bumawiki.global.security.jwt.dto.TokenResponseDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;
import java.util.Date;

import static com.project.bumawiki.global.security.jwt.JwtConstants.*;

@RequiredArgsConstructor
public class JwtProvider {
    private final JwtProperties jwtProperties;
    private final RefreshTokenRepository refreshTokenRepository;

    public TokenResponseDto generateToken(String authId, String role){
        String accessToken = jwtProperties.getPrefix() + EMPTY.getMessage() + generateToken(authId, role, ACCESS_KEY.getMessage() ,jwtProperties.getAccessExp());
        String refreshToken = jwtProperties.getPrefix() + EMPTY.getMessage() + generateToken(authId, role, REFRESH_KEY.getMessage() ,jwtProperties.getRefreshExp());

        refreshTokenRepository.save(RefreshToken.builder()
                .id(authId)
                .refreshToken(refreshToken)
                .ttl(jwtProperties.getRefreshExp() * 1000)
                .build()
        );

        return new TokenResponseDto(accessToken, refreshToken, getExpiredTime());
    }

    private String generateToken(String authId, String role, String type ,Long time){
        return Jwts.builder()
                .setSubject(authId)
                .setHeaderParam(TYPE.message, type)
                .claim(ROLE.getMessage(), role)
                .claim(AUTH_ID.getMessage(), authId)
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecret())
                .compact();
    }

    private ZonedDateTime getExpiredTime(){
        return ZonedDateTime.now().plusSeconds(jwtProperties.getRefreshExp());
    }
}