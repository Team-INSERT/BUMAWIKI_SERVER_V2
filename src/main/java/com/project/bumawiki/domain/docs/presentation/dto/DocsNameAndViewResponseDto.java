package com.project.bumawiki.domain.docs.presentation.dto;

import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.docs.domain.type.DocsType;
import lombok.Getter;

@Getter
public class DocsNameAndViewResponseDto {

    final private Long id;
    final private String title;
    final  private int view;
    final private DocsType docsType;

    public DocsNameAndViewResponseDto(Docs docs){
        this.id = docs.getId();
        this.title = docs.getTitle();
        this.view = docs.getView();
        this.docsType = docs.getDocsType();
    }
}
