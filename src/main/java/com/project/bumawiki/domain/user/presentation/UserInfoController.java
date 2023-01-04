package com.project.bumawiki.domain.user.presentation;

import com.project.bumawiki.domain.user.presentation.dto.UserResponseDto;
import com.project.bumawiki.domain.user.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping
public class UserInfoController {

    private final UserInfoService userInfoService;

    @GetMapping("/")
    public UserResponseDto findUserInfo(){
        return userInfoService.findUserInfo();
    }
}
