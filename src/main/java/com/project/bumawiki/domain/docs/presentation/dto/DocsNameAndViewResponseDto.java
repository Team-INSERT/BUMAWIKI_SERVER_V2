package com.project.bumawiki.domain.docs.presentation.dto;

import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.docs.domain.type.DocsType;

public class DocsNameAndViewResponseDto {

    private Long id;
    private String title;
    private int view;
    private DocsType docsType;

    public DocsNameAndViewResponseDto(Docs docs){
        this.id = docs.getId();
        this.title = docs.getTitle();
        this.view = docs.getView();
        this.docsType = docs.getDocsType();
    }
}
