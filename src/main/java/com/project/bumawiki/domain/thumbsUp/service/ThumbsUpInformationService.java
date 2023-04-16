package com.project.bumawiki.domain.thumbsUp.service;

import com.project.bumawiki.domain.thumbsUp.domain.ThumbsUps;
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

    @Transactional(readOnly = true)
    public List<ThumbsUpResponseDto> getThumbsUpList() {
        Long userId = SecurityUtil.getCurrentUser().getUser().getId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        ThumbsUps thumbsUps = user.getThumbsUps();

        validateThumbsUps(user, thumbsUps);

        return thumbsUps.getList();
    }

    private static void validateThumbsUps(User user, ThumbsUps thumbsUps) {
        if (thumbsUps == null ||
                user.getThumbsUps().getThumbsUpsCount() == 0) {
            throw NoDocsYouThumbsUpException.EXCEPTION;
        }
    }
}

