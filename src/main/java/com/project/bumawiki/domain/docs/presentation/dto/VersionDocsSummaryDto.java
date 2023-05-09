package com.project.bumawiki.domain.docs.presentation.dto;

import com.project.bumawiki.domain.docs.domain.VersionDocs;
import com.project.bumawiki.domain.user.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class VersionDocsSummaryDto {
    private final Long id;
    private final LocalDateTime thisVersionCreatedAt;
    private final Long userId;
    private final String nickName;

    public VersionDocsSummaryDto(VersionDocs versionDocs) {
        User contributor = versionDocs.getContributor().getContributor();
        this.id = versionDocs.getId();
        this.thisVersionCreatedAt = versionDocs.getThisVersionCreatedAt();
        this.nickName = contributor.getNickName();
        this.userId = contributor.getId();
    }
}
