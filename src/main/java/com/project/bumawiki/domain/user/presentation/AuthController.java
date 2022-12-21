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
@RequestMapping
public class AuthController {
    private final UserLoginService userLoginService;
    private final UserLogoutService userLogoutService;

    @GetMapping("/signup/bsm")
    public TokenResponseDto userSignup(@RequestParam String code) throws IOException {
        return userLoginService.execute(code);
    }
}
