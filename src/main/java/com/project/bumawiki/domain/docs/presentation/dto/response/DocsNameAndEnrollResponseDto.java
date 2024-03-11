package com.project.bumawiki.domain.docs.presentation.dto.response;

import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.docs.domain.VersionDocs;
import com.project.bumawiki.domain.docs.domain.type.DocsType;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class DocsNameAndEnrollResponseDto {

    private Long id;
    private String title;
    private int enroll;
    private String simpleContents;
    private DocsType docsType;
    private LocalDateTime lastModifiedAt;

    public DocsNameAndEnrollResponseDto(Docs docs) {
        this.id = docs.getId();
        this.title = docs.getTitle();
        this.enroll = docs.getEnroll();
        this.docsType = docs.getDocsType();
        this.lastModifiedAt = docs.getLastModifiedAt();
        this.simpleContents = getSimpleContents(docs);
    }

    public String getSimpleContents(Docs docs) {
        List<VersionDocs> docsVersion = docs.getDocsVersion();
        int currentDocsSize = docsVersion.size() - 1;
        String contents = docsVersion.get(currentDocsSize).getContents();
        int endIndex = Math.min(contents.length(), 200);
        return contents.substring(0, endIndex);
    }
}
