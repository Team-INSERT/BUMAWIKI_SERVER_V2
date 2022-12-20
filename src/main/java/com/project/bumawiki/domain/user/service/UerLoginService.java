package com.project.bumawiki.domain.user.service;

import com.project.bumawiki.domain.auth.domain.RefreshToken;
import com.project.bumawiki.domain.auth.domain.repository.RefreshTokenRepository;
import com.project.bumawiki.domain.user.User;
import com.project.bumawiki.global.annotation.ServiceWithTransactionalReadOnly;
import com.project.bumawiki.global.jwt.JwtProperties;
import com.project.bumawiki.global.jwt.JwtProvider;
import com.project.bumawiki.global.jwt.dto.TokenResponseDto;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.io.IOException;

@ServiceWithTransactionalReadOnly
@RequiredArgsConstructor
public class UerLoginService {
    private final UserSignUpOrUpdateService userSignUpORUpdateService;
    private final JwtProvider jwtProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    private final JwtProperties jwtProperties;

    @Transactional
    public TokenResponseDto execute(String authId) throws IOException {
        User user = userSignUpORUpdateService.execute(authId);

        return saveRefreshToken(jwtProvider.generateToken(user.getEmail(), user.getAuthority().name()), user.getEmail());
    }

    @Transactional
    protected TokenResponseDto saveRefreshToken(TokenResponseDto tokenResponseDto, String id){
        refreshTokenRepository.save(
                RefreshToken.builder()
                        .id(id)
                        .refreshToken(tokenResponseDto.getRefreshToken())
                        .ttl(jwtProperties.getRefreshExp() * 1000)
                        .build()

        );
        return tokenResponseDto;
    }
}
