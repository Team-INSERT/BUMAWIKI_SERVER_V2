package com.project.bumawiki.domain.docs.presentation.dto;

import com.project.bumawiki.domain.contribute.domain.Contribute;
import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.docs.domain.VersionDocs;
import com.project.bumawiki.domain.docs.domain.type.DocsType;
import com.project.bumawiki.domain.user.entity.User;
import lombok.Getter;

import java.sql.Clob;
import java.util.ArrayList;
import java.util.List;

@Getter
public class DocsResponseDto {

    private String title;
    private String contents;
    private DocsType docsType;
    private int enroll;
    private int view;
    private List<User> contributor = new ArrayList<>();

    public DocsResponseDto(Docs docs) {
        VersionDocs versionDocs = docs.getDocsVersion().get(0);
        List<Contribute> contributes = docs.getContributor();
        this.title = versionDocs.getTitle();
        this.contents = versionDocs.getContents();
        this.contents = versionDocs.getContents();
        this.docsType = docs.getDocsType();
        this.enroll = versionDocs.getEnroll();
        this.view = docs.getView();
        for (Contribute contribute : contributes) {
            this.contributor.add(contribute.getContributor());
        }
    }
}
