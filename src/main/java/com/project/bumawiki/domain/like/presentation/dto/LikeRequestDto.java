package com.project.bumawiki.domain.like.presentation.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class LikeRequestDto {

    @NotBlank
    private Long docsId;
}
