package com.project.bumawiki.domain.auth.presentation;

import com.project.bumawiki.domain.auth.service.AccessTokenRefreshService;
import com.project.bumawiki.domain.user.service.UserLoginService;
import com.project.bumawiki.domain.user.service.UserLogoutService;
import com.project.bumawiki.global.jwt.dto.TokenResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserLoginService userLoginService;
    private final UserLogoutService userLogoutService;
    private final AccessTokenRefreshService accessTokenRefreshService;

    @PostMapping("/oauth/bsm")
    public TokenResponseDto userSignup(@RequestHeader("authCode") String authCode) throws IOException {
        return ResponseEntity.ok(userLoginService.execute(authCode)).getBody();
    }

    @DeleteMapping("/bsm/logout")
    public ResponseEntity<String> userLogout(@RequestHeader("refresh_token") String refreshToken) {
        return ResponseEntity.ok(userLogoutService.execute(refreshToken));
    }

    @PutMapping("/refresh/access")
    public TokenResponseDto refreshAccessToken(@RequestHeader("refresh_token") String refreshToken) {
        return ResponseEntity.ok(accessTokenRefreshService.execute(refreshToken)).getBody();
    }
}
