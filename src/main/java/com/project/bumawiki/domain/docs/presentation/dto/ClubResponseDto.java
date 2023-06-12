package com.project.bumawiki.domain.docs.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.bumawiki.domain.docs.presentation.dto.response.DocsNameAndEnrollResponseDto;

import java.util.List;

public class ClubResponseDto {
    @JsonProperty
    private List<DocsNameAndEnrollResponseDto> club;
    @JsonProperty
    private List<DocsNameAndEnrollResponseDto> freeClub;

    public ClubResponseDto(List<DocsNameAndEnrollResponseDto> club, List<DocsNameAndEnrollResponseDto> freeClub) {
        this.club = club;
        this.freeClub = freeClub;
    }
}
