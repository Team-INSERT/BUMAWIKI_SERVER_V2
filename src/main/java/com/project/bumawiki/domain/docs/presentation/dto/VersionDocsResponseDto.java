package com.project.bumawiki.domain.docs.presentation.dto;

import com.project.bumawiki.domain.docs.domain.VersionDocs;
import com.project.bumawiki.domain.user.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
public class VersionDocsResponseDto {
    private String contents;
    private LocalDateTime thisVersionCreatedAt;
    private Long userId;
    private String nickName;

    public VersionDocsResponseDto(VersionDocs versionDocs){
        User contributor = versionDocs.getContributor().getContributor();
        this.contents = versionDocs.getContents();
        this.thisVersionCreatedAt = versionDocs.getThisVersionCreatedAt();
        this.nickName = contributor.getNickName();
        this.userId = contributor.getId();
    }
}
