package com.project.bumawiki.domain.docs.presentation.dto;

import com.project.bumawiki.domain.docs.domain.Docs;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class DocsNameAndEnrollResponseDto {
    private String title;
    private int enroll;

    public DocsNameAndEnrollResponseDto(Docs docs){
        this.title = docs.getTitle();
        this.enroll = docs.getEnroll();
    }
}
