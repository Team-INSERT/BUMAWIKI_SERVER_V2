package com.project.bumawiki.domain.docs.presentation.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class VersionDocsResponseDto {
    private final LocalDateTime thisVersionCreatedAt;
    private final Long userId;
    private final String nickName;

    public VersionDocsResponseDto(LocalDateTime thisVersionCreatedAt, Long userId, String nickName) {
        this.thisVersionCreatedAt = thisVersionCreatedAt;
        this.nickName = nickName;
        this.userId = userId;
    }
}
