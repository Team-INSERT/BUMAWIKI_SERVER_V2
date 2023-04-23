package com.project.bumawiki.domain.docs.presentation.dto;

import com.project.bumawiki.domain.docs.domain.VersionDocs;
import com.project.bumawiki.domain.user.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class VersionDocsSummaryDto {
    private Long id;
    private LocalDateTime thisVersionCreatedAt;
    private Long userId;
    private String nickName;

    public VersionDocsSummaryDto(VersionDocs versionDocs) {
        User contributor = versionDocs.getContributor().getContributor();
        this.id = versionDocs.getId();
        this.thisVersionCreatedAt = versionDocs.getThisVersionCreatedAt();
        this.nickName = contributor.getNickName();
        this.userId = contributor.getId();
    }
}
