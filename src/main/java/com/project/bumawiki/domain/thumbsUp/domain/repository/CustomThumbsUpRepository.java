package com.project.bumawiki.domain.thumbsUp.domain.repository;

import com.project.bumawiki.domain.thumbsUp.presentation.dto.ThumbsUpResponseDto;
import com.project.bumawiki.domain.user.entity.User;

import java.util.List;

public interface CustomThumbsUpRepository {
    List<ThumbsUpResponseDto> getUserThumbsUp(User user);
}
