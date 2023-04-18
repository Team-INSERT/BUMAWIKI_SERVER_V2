package com.project.bumawiki.domain.docs.presentation.dto.response;

import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.docs.domain.type.DocsType;
import com.project.bumawiki.domain.user.entity.authority.Authority;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class DocsNameAndEnrollResponseDto {

    private Long id;
    private String title;
    private int enroll;
    private DocsType docsType;
    private LocalDateTime lastModifiedAt;

    public DocsNameAndEnrollResponseDto(Docs docs) {
        this.id = docs.getId();
        this.title = docs.getTitle();
        this.enroll = docs.getEnroll();
        this.docsType = docs.getDocsType();
        this.lastModifiedAt = docs.getLastModifiedAt();
    }
}
