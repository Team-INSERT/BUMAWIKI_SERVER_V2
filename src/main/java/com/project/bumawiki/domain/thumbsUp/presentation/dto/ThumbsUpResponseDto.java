package com.project.bumawiki.domain.thumbsUp.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.bumawiki.domain.docs.domain.type.DocsType;

public class ThumbsUpResponseDto {

    @JsonProperty
    private final String title;
    @JsonProperty
    private final DocsType docsType;

    public ThumbsUpResponseDto(String title, DocsType docsType) {
        this.title = title;
        this.docsType = docsType;
    }
}
