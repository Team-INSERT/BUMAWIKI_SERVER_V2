package com.project.bumawiki.domain.thumbsUp.service;

import com.project.bumawiki.domain.thumbsUp.domain.collection.UserThumbsUps;
import com.project.bumawiki.domain.thumbsUp.domain.repository.CustomThumbsUpRepository;
import com.project.bumawiki.domain.thumbsUp.exception.NoDocsYouThumbsUpException;
import com.project.bumawiki.domain.thumbsUp.presentation.dto.ThumbsUpResponseDto;
import com.project.bumawiki.domain.user.UserFacade;
import com.project.bumawiki.domain.user.entity.User;
import com.project.bumawiki.domain.user.entity.repository.UserRepository;
import com.project.bumawiki.domain.user.exception.UserNotFoundException;
import com.project.bumawiki.global.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ThumbsUpInformationService {

    private final CustomThumbsUpRepository customThumbsUpRepository;

    public List<ThumbsUpResponseDto> getThumbsUpList() {
//        User user = SecurityUtil.getCurrentUserOrNotLogin();
//
//        return customThumbsUpRepository.getUserThumbsUp(user);
        return null;
    }
}

