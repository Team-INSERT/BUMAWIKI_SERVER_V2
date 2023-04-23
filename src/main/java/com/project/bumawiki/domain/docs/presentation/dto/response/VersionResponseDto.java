package com.project.bumawiki.domain.docs.presentation.dto.response;

import com.project.bumawiki.domain.docs.presentation.dto.response.DocsResponseDto;
import com.project.bumawiki.domain.docs.presentation.dto.response.VersionDocsResponseDto;
import lombok.Getter;

import java.util.List;

@Getter
public class VersionResponseDto {
    public DocsResponseDto docsResponseDto;
    public List<VersionDocsResponseDto> versionDocsResponseDto;

    public VersionResponseDto(DocsResponseDto docsResponseDto, List<VersionDocsResponseDto> versionDocsResponseDto) {
        this.docsResponseDto = docsResponseDto;
        this.versionDocsResponseDto = versionDocsResponseDto;
    }
}
