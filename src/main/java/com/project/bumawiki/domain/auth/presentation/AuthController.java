package com.project.bumawiki.domain.auth.presentation;

import com.project.bumawiki.domain.auth.presentation.dto.RefreshTokenRequestBodyDto;
import com.project.bumawiki.domain.auth.service.AccessTokenRefreshService;
import com.project.bumawiki.domain.user.service.UserLoginService;
import com.project.bumawiki.domain.user.service.UserLogoutService;
import com.project.bumawiki.global.jwt.dto.TokenResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
    public TokenResponseDto userSignup(HttpServletRequest request) throws IOException {
        return userLoginService.execute(request.getHeader("authCode"));
    }

    @DeleteMapping("/bsm/logout")
    public String userLogout(@RequestBody RefreshTokenRequestBodyDto request) {
        return userLogoutService.execute(request.getRefresh_token());
    }

    @PutMapping("/refresh/access")
    public TokenResponseDto refreshAccessToken(@RequestBody RefreshTokenRequestBodyDto request){
        return accessTokenRefreshService.execute(request.getRefresh_token());
    }
}
