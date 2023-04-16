package com.project.bumawiki.domain.thumbsUp.presentation.dto;

import com.project.bumawiki.domain.docs.domain.type.DocsType;
import com.project.bumawiki.domain.thumbsUp.domain.ThumbsUp;

public class ThumbsUpResponseDto {

    private final String title;
    private final DocsType docsType;

    public ThumbsUpResponseDto(ThumbsUp thumbsUp) {
        title = thumbsUp.getDocs().getTitle();
        docsType = thumbsUp.getDocs().getDocsType();
    }
}
