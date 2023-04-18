package com.project.bumawiki.domain.docs.presentation.dto.response;

import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.docs.domain.VersionDocs;
import com.project.bumawiki.domain.docs.domain.type.DocsType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DocsResponseDto {

    private final Long id;
    private final String title;
    private final String contents;
    private final DocsType docsType;
    private final LocalDateTime lastModifiedAt;
    private final int enroll;
    private final int thumbsUpsCounts;
    private boolean youLikeThis;

    public DocsResponseDto(Docs docs) {
        int lastValueOfDocsVersion = docs.getDocsVersion().size() - 1;
        VersionDocs versionDocs = docs.getDocsVersion().get(lastValueOfDocsVersion);

        this.id = docs.getId();
        this.title = docs.getTitle();
        this.contents = versionDocs.getContents();
        this.lastModifiedAt = docs.getLastModifiedAt();
        this.docsType = docs.getDocsType();
        this.enroll = docs.getEnroll();
        this.thumbsUpsCounts = docs.getThumbsUpsCount();
        this.youLikeThis = false;
    }

    public DocsResponseDto setYouLikeThis(boolean youLikeThis) {
        this.youLikeThis = youLikeThis;
        return this;
    }
}

