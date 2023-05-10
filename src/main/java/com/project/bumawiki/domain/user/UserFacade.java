package com.project.bumawiki.domain.user;

import com.project.bumawiki.domain.user.entity.User;
import com.project.bumawiki.domain.user.entity.repository.UserRepository;
import com.project.bumawiki.domain.user.exception.UserNotFoundException;
import com.project.bumawiki.domain.user.exception.UserNotLoginException;
import com.project.bumawiki.global.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {
    private final UserRepository userRepository;

    @Nullable
    public User getCurrentUser() {
        User currentUserWithLogin = SecurityUtil
                .getCurrentUserOrNotLogin();

        return userRepository
                .findById(currentUserWithLogin.getId())
                .orElseThrow(() -> UserNotLoginException.EXCEPTION);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }
}
