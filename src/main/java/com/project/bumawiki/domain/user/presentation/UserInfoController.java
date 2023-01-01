package com.project.bumawiki.domain.user.presentation;

import com.project.bumawiki.domain.user.presentation.dto.UserResponseDto;
import com.project.bumawiki.domain.user.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class UserInfoController {

    private final UserInfoService userInfoService;

    @GetMapping("/")
    public UserResponseDto findUserInfo(){
        return userInfoService.findUserInfo();
    }
}
