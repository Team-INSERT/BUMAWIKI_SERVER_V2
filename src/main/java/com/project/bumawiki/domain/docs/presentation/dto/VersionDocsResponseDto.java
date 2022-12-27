package com.project.bumawiki.domain.docs.presentation.dto;

import com.project.bumawiki.domain.docs.domain.VersionDocs;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
public class VersionDocsResponseDto {
    private String contents;
    private LocalDateTime thisVersionCreatedAt;

    public VersionDocsResponseDto(VersionDocs versionDocs){
        this.contents = versionDocs.getContents();
        this.thisVersionCreatedAt = versionDocs.getThisVersionCreatedAt();
    }
}
