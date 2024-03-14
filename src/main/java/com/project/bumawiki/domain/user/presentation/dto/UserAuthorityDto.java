package com.project.bumawiki.domain.user.presentation.dto;

import com.project.bumawiki.domain.user.domain.authority.Authority;
import lombok.AllArgsConstructor;
import lombok.Getter;

import jakarta.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class UserAuthorityDto {

    @NotNull
    private String email;
    @NotNull
    private Authority authority;

}
