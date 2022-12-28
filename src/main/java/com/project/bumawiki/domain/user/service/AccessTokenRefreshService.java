package com.project.bumawiki.domain.user.service;

import com.project.bumawiki.domain.auth.domain.RefreshToken;
import com.project.bumawiki.domain.auth.domain.repository.RefreshTokenRepository;
import com.project.bumawiki.global.jwt.config.JwtProperties;
import com.project.bumawiki.global.jwt.dto.TokenResponseDto;
import com.project.bumawiki.global.jwt.exception.RefreshTokenNotFoundException;
import com.project.bumawiki.global.jwt.util.JwtProvider;
import com.project.bumawiki.global.jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@RequiredArgsConstructor
@Service
public class AccessTokenRefreshService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;

    public TokenResponseDto execute(String bearerRefreshToken) {
        RefreshToken redisRefreshToken = refreshTokenRepository.findByRefreshToken(bearerRefreshToken)
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);
        return getNewAccessTokens(redisRefreshToken);
    }

    private TokenResponseDto getNewAccessTokens(RefreshToken redisRefreshToken) {


        String newAccessToken = jwtProvider.generateToken(redisRefreshToken.getId(), redisRefreshToken.getRole()).getAccessToken();

        return TokenResponseDto.builder()
                .accessToken(newAccessToken)
                .refreshToken(redisRefreshToken.getRefreshToken())
                .expiredAt(ZonedDateTime.now().plusSeconds(redisRefreshToken.getTtl() / 1000))
                .build();
    }
}
