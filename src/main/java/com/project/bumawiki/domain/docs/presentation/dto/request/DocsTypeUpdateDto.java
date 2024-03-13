package com.project.bumawiki.domain.docs.presentation.dto.request;

import com.project.bumawiki.domain.docs.domain.type.DocsType;
import lombok.Getter;

import jakarta.validation.constraints.NotBlank;

@Getter
public class DocsTypeUpdateDto {

    @NotBlank
    Long id;
    @NotBlank
    DocsType docsType;

}
