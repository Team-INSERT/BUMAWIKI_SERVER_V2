package com.project.bumawiki.domain.like.presentation.dto;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotBlank;

@Getter
public class LikeRequestDto {

    @NotBlank
    private Long docsId;
}
