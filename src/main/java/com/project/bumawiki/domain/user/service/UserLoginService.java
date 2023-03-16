package com.project.bumawiki.domain.user.service;

import com.project.bumawiki.domain.auth.domain.AuthId;
import com.project.bumawiki.domain.auth.domain.repository.AuthIdRepository;
import com.project.bumawiki.domain.auth.service.UserSignUpOrUpdateService;
import com.project.bumawiki.domain.user.entity.User;
import com.project.bumawiki.global.annotation.ServiceWithTransactionalReadOnly;
import com.project.bumawiki.global.jwt.config.JwtProperties;
import com.project.bumawiki.global.jwt.util.JwtProvider;
import com.project.bumawiki.global.jwt.dto.TokenResponseDto;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@ServiceWithTransactionalReadOnly
@RequiredArgsConstructor
public class UserLoginService {
    private final UserSignUpOrUpdateService userSignUpORUpdateService;
    private final JwtProvider jwtProvider;
    private final JwtProperties jwtProperties;
    private final AuthIdRepository authIdRepository;


    public TokenResponseDto execute(String authId) throws IOException {

        User user = userSignUpORUpdateService.execute(authId);
        saveAuthId(user.getEmail());

        return jwtProvider.generateToken(user.getEmail(), user.getAuthority().name());
    }

    private void saveAuthId(String email){
        authIdRepository.save(
                AuthId.builder()
                        .id(email)
                        .authId(email)
                        .ttl(jwtProperties.getRefreshExp())
                        .build()
        );
    }
}
