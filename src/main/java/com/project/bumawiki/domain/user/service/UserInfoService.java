package com.project.bumawiki.domain.user.service;

import com.project.bumawiki.domain.user.entity.User;
import com.project.bumawiki.domain.user.entity.repository.UserRepository;
import com.project.bumawiki.domain.user.exception.UserNotLoginException;
import com.project.bumawiki.domain.user.presentation.dto.UserResponseDto;
import com.project.bumawiki.global.annotation.ServiceWithTransactionalReadOnly;
import com.project.bumawiki.global.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@ServiceWithTransactionalReadOnly
@RequiredArgsConstructor
@Transactional
public class UserInfoService {

    public UserResponseDto findUserInfo(){
        User user = SecurityUtil.getCurrentUser().getUser();
        if(user == null){
            throw UserNotLoginException.EXCEPTION;
        }

        return new UserResponseDto(user);
    }
}
