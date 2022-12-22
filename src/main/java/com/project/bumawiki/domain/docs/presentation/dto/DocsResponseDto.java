package com.project.bumawiki.domain.docs.presentation.dto;

import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.docs.domain.VersionDocs;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.List;

@Getter
public class DocsResponseDto {

    @NotNull
    private String title;

    @NotNull
    private int enroll;

    @NotNull
    private String contents;

    private List<Clob> imageLink = new ArrayList<>();

    public DocsResponseDto(VersionDocs versionDocs) {
        this.title = versionDocs.getTitle();
        this.enroll = versionDocs.getEnroll();
        this.contents = versionDocs.getContents();
        this.imageLink = versionDocs.getImageLink();
    }
}
