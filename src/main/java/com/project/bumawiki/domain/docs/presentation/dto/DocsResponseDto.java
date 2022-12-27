package com.project.bumawiki.domain.docs.presentation.dto;

import com.project.bumawiki.domain.contribute.domain.Contribute;
import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.docs.domain.VersionDocs;
import com.project.bumawiki.domain.docs.domain.type.DocsType;
import lombok.Getter;

import java.util.List;

@Getter
public class DocsResponseDto {

    private Long id;

    private String title;

    private String contents;

    private DocsType docsType;
    private int enroll;
    private int view;

    public DocsResponseDto(Docs docs) {
        VersionDocs versionDocs = docs.getDocsVersion().get(0);
        List<Contribute> contributes = docs.getContributor();
        this.id = docs.getId();
        this.title = docs.getTitle();
        this.contents = versionDocs.getContents();
        this.docsType = docs.getDocsType();
        this.enroll = docs.getEnroll();
        this.view = docs.getView();
    }
}

