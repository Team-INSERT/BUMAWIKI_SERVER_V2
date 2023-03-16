package com.project.bumawiki.domain.user.service;

import com.project.bumawiki.domain.user.entity.User;
import com.project.bumawiki.domain.user.entity.authority.Authority;
import com.project.bumawiki.domain.user.entity.repository.UserRepository;
import com.project.bumawiki.domain.user.exception.UserNotFoundException;
import com.project.bumawiki.domain.user.presentation.dto.UserAuthorityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class UserAuthorityService {
    private final UserRepository userRepository;

    public Authority execute(final UserAuthorityDto userAuthorityDto) {
        Optional<User> userFindByEmail = userRepository.findByEmail(userAuthorityDto.getEmail());

        if(userFindByEmail.isEmpty()){
            throw UserNotFoundException.EXCEPTION;
        }

        User user = userFindByEmail.get();
        user.changeUserAuthority(userAuthorityDto.getAuthority());

        return user.getAuthority();
    }
}
