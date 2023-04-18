package com.project.bumawiki.domain.user;

import com.project.bumawiki.domain.user.entity.User;
import com.project.bumawiki.domain.user.entity.repository.UserRepository;
import com.project.bumawiki.domain.user.exception.UserNotFoundException;
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

        if (currentUserWithLogin == null) {
            return null;
        }

        return userRepository
                .findById(currentUserWithLogin.getId())
                .orElse(null);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }
}
