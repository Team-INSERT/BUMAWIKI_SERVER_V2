package com.project.bumawiki.domain.docs.presentation.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class VersionDocsResponseDto {
    private final Long versionDocsId;
    private final LocalDateTime thisVersionCreatedAt;
    private final Long userId;
    private final String nickName;

    public VersionDocsResponseDto(Long versionDocsId, LocalDateTime thisVersionCreatedAt, Long userId, String nickName) {
        this.versionDocsId = versionDocsId;
        this.thisVersionCreatedAt = thisVersionCreatedAt;
        this.nickName = nickName;
        this.userId = userId;
    }
}
