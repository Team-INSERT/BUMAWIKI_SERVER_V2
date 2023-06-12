package com.project.bumawiki.domain.docs.presentation.dto.request;

import com.project.bumawiki.domain.docs.domain.type.DocsType;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class DocsCreateRequestDto {

    @NotBlank
    private String title;

    @NotBlank
    private int enroll;

    @NotBlank
    private String contents;

    @NotBlank
    private DocsType docsType;

    public void updateContent(String setContent) {
        this.contents = setContent;
    }
}
