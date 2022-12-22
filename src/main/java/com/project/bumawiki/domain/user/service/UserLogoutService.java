package com.project.bumawiki.domain.user.service;

import com.project.bumawiki.domain.auth.domain.repository.AuthIdRepository;
import com.project.bumawiki.domain.auth.domain.repository.RefreshTokenRepository;
import com.project.bumawiki.domain.user.exception.UserNotFoundException;
import com.project.bumawiki.global.jwt.config.JwtConstants;
import com.project.bumawiki.global.jwt.exception.InvalidJwtException;
import com.project.bumawiki.global.jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class UserLogoutService {

    private final JwtUtil jwtUtil;
    private final AuthIdRepository authIdRepository;
    private final RefreshTokenRepository refreshTokenRepository;


    @Transactional
    public String execute(String bearRefreshToken){
        String authId = jwtUtil.getJwtBody(bearRefreshToken).get(JwtConstants.AUTH_ID.message).toString();
        authIdRepository.delete(
                authIdRepository.findByAuthId(authId).orElseThrow(() -> UserNotFoundException.EXCEPTION)
        );

        refreshTokenRepository.delete(
                refreshTokenRepository.findById(authId).orElseThrow(() -> InvalidJwtException.EXCEPTION)
        );
        return authId;
    }
}
