package com.project.bumawiki.domain.user.service;

import com.project.bumawiki.domain.user.User;
import com.project.bumawiki.domain.user.authority.Authority;
import com.project.bumawiki.domain.user.exception.UserNotFoundException;
import com.project.bumawiki.domain.user.repository.UserRepository;
import com.project.bumawiki.global.oauth.exception.BsmAuthIdInvalidClientException;
import leehj050211.bsmOauth.BsmOauth;
import leehj050211.bsmOauth.dto.response.BsmResourceResponse;
import leehj050211.bsmOauth.exceptions.BsmAuthCodeNotFoundException;
import leehj050211.bsmOauth.exceptions.BsmAuthInvalidClientException;
import leehj050211.bsmOauth.exceptions.BsmAuthTokenNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;

@RequiredArgsConstructor
@Service
public class UserSignUpService {
    private final BsmOauth bsmOauth;
    private final UserRepository userRepository;

    public User bsmOauth(String authId) throws IOException {
        String token;
        BsmResourceResponse resource;
        try {
            token = bsmOauth.getToken(authId);
            resource = bsmOauth.getResource(token);
        }catch(BsmAuthCodeNotFoundException | BsmAuthTokenNotFoundException e){
            throw UserNotFoundException.EXCEPTION;
        }catch(BsmAuthInvalidClientException e){
            throw BsmAuthIdInvalidClientException.EXCEPTION;
        }

        return updateOrSignUp(resource);
    }

    @Transactional
    protected User updateOrSignUp(BsmResourceResponse resource) {
        User user = userRepository.findByEmail(resource.getEmail())
                .orElse(saveUser(resource));

        return user.update(resource);
    }

    @Transactional
    protected User saveUser(BsmResourceResponse resource) {
         return userRepository.save(
                User.builder()
                .email(resource.getEmail())
                .nickName(resource.getNickname())
                .authority(Authority.USER)
                .build()
                );
    }

}
