package com.project.bumawiki.domain.docs.presentation.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class DocsTitleUpdateRequestDto {
    @NotBlank
    private String title;
}
