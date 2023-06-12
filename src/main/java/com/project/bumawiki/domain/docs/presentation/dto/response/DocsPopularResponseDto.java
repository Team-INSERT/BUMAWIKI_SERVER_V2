package com.project.bumawiki.domain.docs.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.bumawiki.domain.docs.domain.repository.wrapper.DocsPopularWrapper;
import com.project.bumawiki.domain.docs.domain.type.DocsType;

public class DocsPopularResponseDto {
    @JsonProperty
    private final String title;
    @JsonProperty
    private final int enroll;
    @JsonProperty
    private final DocsType docsType;
    @JsonProperty
    private final int thumbsUpsCounts;

    public DocsPopularResponseDto(DocsPopularWrapper docsPopularWrapper) {
        this.title = docsPopularWrapper.getTitle();
        this.enroll = docsPopularWrapper.getEnroll();
        this.docsType = docsPopularWrapper.getDocsType();
        this.thumbsUpsCounts = docsPopularWrapper.getThumbsUpCount();
    }
}
