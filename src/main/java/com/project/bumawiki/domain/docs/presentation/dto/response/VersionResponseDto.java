package com.project.bumawiki.domain.docs.presentation.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class VersionResponseDto {
    public int length;
    public List<VersionDocsResponseDto> versionDocsResponseDto;

    public VersionResponseDto(List<VersionDocsResponseDto> versionDocsResponseDto) {
        length = versionDocsResponseDto.size();
        this.versionDocsResponseDto = versionDocsResponseDto;
    }
}
