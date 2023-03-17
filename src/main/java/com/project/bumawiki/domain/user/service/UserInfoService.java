package com.project.bumawiki.domain.user.service;

import com.project.bumawiki.domain.user.entity.User;
import com.project.bumawiki.domain.user.entity.repository.UserRepository;
import com.project.bumawiki.domain.user.exception.UserNotFoundException;
import com.project.bumawiki.domain.user.exception.UserNotLoginException;
import com.project.bumawiki.domain.user.presentation.dto.UserResponseDto;
import com.project.bumawiki.global.annotation.ServiceWithTransactionalReadOnly;
import com.project.bumawiki.global.util.SecurityUtil;
import lombok.RequiredArgsConstructor;

@ServiceWithTransactionalReadOnly
@RequiredArgsConstructor
public class UserInfoService {

    private final UserRepository userRepository;

    public UserResponseDto findMyInfo(){
        User user1 = SecurityUtil.getCurrentUser().getUser();
        User user = userRepository.findById(user1.getId())
                .orElseThrow(() -> UserNotLoginException.EXCEPTION);

        return new UserResponseDto(user);
    }
    
    public UserResponseDto findAnotherInfo(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);


        return new UserResponseDto(user);
    }
}
