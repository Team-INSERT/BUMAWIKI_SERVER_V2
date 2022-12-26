package com.project.bumawiki.domain.user.service;

import com.project.bumawiki.domain.auth.domain.RefreshToken;
import com.project.bumawiki.domain.auth.domain.repository.RefreshTokenRepository;
import com.project.bumawiki.global.jwt.config.JwtProperties;
import com.project.bumawiki.global.jwt.dto.TokenResponseDto;
import com.project.bumawiki.global.jwt.exception.RefreshTokenNotFoundException;
import com.project.bumawiki.global.jwt.util.JwtProvider;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TokenRefreshService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;
    private final JwtProperties jwtProperties;

    public TokenResponseDto execute(String refreshToken) {
        RefreshToken redisRefreshToken = refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);
        return getNewTokens(redisRefreshToken);
    }

    private TokenResponseDto getNewTokens(RefreshToken redisRefreshToken) {
        String newRefreshToken = jwtProvider.generateToken(redisRefreshToken.getId(), redisRefreshToken.getRole()).getRefreshToken();
        redisRefreshToken.update(newRefreshToken, jwtProperties.getRefreshExp());

        String newAccessToken = jwtProvider.generateToken(redisRefreshToken.getId(), redisRefreshToken.getRole()).getAccessToken();

        return TokenResponseDto.builder()
                .accessToken(newAccessToken)
                .refreshToken(newRefreshToken)
                .expiredAt(jwtProvider.getExpiredTime())
                .build();
    }
}
