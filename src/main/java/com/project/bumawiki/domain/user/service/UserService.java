package com.project.bumawiki.domain.user.service;

import com.project.bumawiki.domain.user.User;
import leehj050211.bsmOauth.BsmOauth;
import leehj050211.bsmOauth.dto.response.BsmResourceResponse;
import leehj050211.bsmOauth.exceptions.BsmAuthCodeNotFoundException;
import leehj050211.bsmOauth.exceptions.BsmAuthInvalidClientException;
import leehj050211.bsmOauth.exceptions.BsmAuthTokenNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class UserService {
    private final BsmOauth bsmOauth;

    public User bsmOauth(String authId) throws BsmAuthInvalidClientException, BsmAuthCodeNotFoundException, IOException, BsmAuthTokenNotFoundException {
        try {
            String token = bsmOauth.getToken(authId);
            BsmResourceResponse resource = bsmOauth.getResource(token);
        }catch(BsmAuthCodeNotFoundException | BsmAuthTokenNotFoundException e){

        }
    }
}
