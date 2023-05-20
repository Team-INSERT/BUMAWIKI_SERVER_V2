package com.project.bumawiki.domain.docs.presentation.dto.response;

import com.project.bumawiki.domain.docs.domain.VersionDocs;
import com.project.bumawiki.domain.user.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class VersionDocsResponseDto {
    private final Long id;
    private final LocalDateTime thisVersionCreatedAt;
    private final Long userId;
    private final String nickName;

    public VersionDocsResponseDto(VersionDocs versionDocs, User contributor) {
        this.id = versionDocs.getId();
        this.thisVersionCreatedAt = versionDocs.getThisVersionCreatedAt();
        this.nickName = contributor.getNickName();
        this.userId = contributor.getId();
    }
}
