package com.project.bumawiki.domain.thumbsUp.service;

import com.project.bumawiki.domain.thumbsUp.presentation.dto.ThumbsUpResponseDto;
import com.project.bumawiki.domain.user.entity.User;
import com.project.bumawiki.global.util.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ThumbsUpInformationService {

    @Transactional(readOnly = true)
    public List<ThumbsUpResponseDto> getThumbsUpList() {
        User user = SecurityUtil.getCurrentUser().getUser();

        return user.getThumbsUps().getList();
    }
}

