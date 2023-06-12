package com.project.bumawiki.domain.thumbsUp.service;

import com.project.bumawiki.domain.thumbsUp.domain.repository.CustomThumbsUpRepository;
import com.project.bumawiki.domain.thumbsUp.presentation.dto.ThumbsUpResponseDto;
import com.project.bumawiki.domain.user.domain.User;
import com.project.bumawiki.global.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ThumbsUpInformationService {

    private final CustomThumbsUpRepository customThumbsUpRepository;

    public List<ThumbsUpResponseDto> getThumbsUpList() {
        User user = SecurityUtil.getCurrentUserOrNotLogin();

        return customThumbsUpRepository.getUserThumbsUp(user);
    }
}

