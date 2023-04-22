package com.project.bumawiki.domain.thumbsUp.service;

import com.project.bumawiki.domain.thumbsUp.domain.collection.UserThumbsUps;
import com.project.bumawiki.domain.thumbsUp.exception.NoDocsYouThumbsUpException;
import com.project.bumawiki.domain.thumbsUp.presentation.dto.ThumbsUpResponseDto;
import com.project.bumawiki.domain.user.entity.User;
import com.project.bumawiki.domain.user.entity.repository.UserRepository;
import com.project.bumawiki.domain.user.exception.UserNotFoundException;
import com.project.bumawiki.global.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ThumbsUpInformationService {

    private final UserRepository userRepository;

    private static void validateThumbsUps(User user, UserThumbsUps userThumbsUps) {
        if (userThumbsUps == null ||
                user.getUserThumbsUps().getThumbsUpsCount() == 0) {
            throw NoDocsYouThumbsUpException.EXCEPTION;
        }
    }

    @Transactional(readOnly = true)
    public List<ThumbsUpResponseDto> getThumbsUpList() {
        Long userId = SecurityUtil.getCurrentUserWithLogin().getId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        UserThumbsUps userThumbsUps = user.getUserThumbsUps();

        validateThumbsUps(user, userThumbsUps);

        return userThumbsUps.getList();
    }
}

