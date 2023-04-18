package com.project.bumawiki.domain.docs.presentation.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class DocsUpdateRequestDto {

    @NotNull
    private String contents;

    public DocsUpdateRequestDto updateContent(String setContent){
        this.contents = setContent;
        return this;
    }
}
