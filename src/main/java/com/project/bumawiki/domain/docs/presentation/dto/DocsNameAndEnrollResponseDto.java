package com.project.bumawiki.domain.docs.presentation.dto;

import com.project.bumawiki.domain.docs.domain.Docs;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class DocsNameAndEnrollResponseDto {

    private Long id;
    private String title;
    private int enroll;

    public DocsNameAndEnrollResponseDto(Docs docs){
        this.id = docs.getId();
        this.title = docs.getTitle();
        this.enroll = docs.getEnroll();
    }
}
