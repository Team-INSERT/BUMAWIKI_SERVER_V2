package com.project.bumawiki.domain.user.service;

import com.project.bumawiki.domain.auth.domain.AuthId;
import com.project.bumawiki.domain.auth.domain.RefreshToken;
import com.project.bumawiki.domain.auth.domain.repository.AuthIdRepository;
import com.project.bumawiki.domain.auth.domain.repository.RefreshTokenRepository;
import com.project.bumawiki.domain.user.entity.User;
import com.project.bumawiki.global.annotation.ServiceWithTransactionalReadOnly;
import com.project.bumawiki.global.jwt.config.JwtProperties;
import com.project.bumawiki.global.jwt.util.JwtProvider;
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
    private final AuthIdRepository authIdRepository;

    @Transactional
    public TokenResponseDto execute(String authId) throws IOException {

        User user = userSignUpORUpdateService.execute(authId);
        saveAuthId(user.getEmail());

        return saveRefreshToken(jwtProvider.generateToken(user.getEmail(), user.getAuthority().name()), user.getEmail());
    }

    @Transactional
    protected void saveAuthId(String email){
        authIdRepository.save(new AuthId().update(email));
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
