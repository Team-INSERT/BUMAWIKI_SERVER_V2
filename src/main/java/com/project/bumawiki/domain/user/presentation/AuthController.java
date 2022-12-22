package com.project.bumawiki.domain.user.presentation;

import com.project.bumawiki.domain.user.entity.User;
import com.project.bumawiki.domain.user.service.UserLoginService;
import com.project.bumawiki.domain.user.service.UserLogoutService;
import com.project.bumawiki.global.jwt.dto.TokenResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserLoginService userLoginService;
    private final UserLogoutService userLogoutService;

    @PostMapping("/oauth/bsm")
    public TokenResponseDto userSignup(HttpServletRequest request) throws IOException {
        return userLoginService.execute(request.getHeader("authCode"));
    }

    @PostMapping("/bsm/logout")
    public String userLogout(@RequestHeader("refresh_token")String refreshToken) throws IOException {
        return userLogoutService.execute(refreshToken);
    }
}
