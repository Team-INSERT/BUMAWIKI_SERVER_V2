package com.project.bumawiki.domain.user.presentation.dto;

import com.project.bumawiki.domain.contribute.domain.Contribute;
import com.project.bumawiki.domain.contribute.dto.ContributeResponseDto;
import com.project.bumawiki.domain.user.entity.User;
import com.project.bumawiki.domain.user.entity.authority.Authority;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserResponseDto {
    private Long id;

    private String email;

    private String nickName;

    private Authority authority;

    private List<ContributeResponseDto> contributeDocs;

    public UserResponseDto(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.nickName = user.getNickName();
        this.authority = user.getAuthority();
        List<Contribute> contributeDocs = getContributeReversed(user);
        this.contributeDocs = contributeDocs
                        .stream()
                        .map(ContributeResponseDto::new)
                        .collect(Collectors.toList());
    }

    @NotNull
    private static List<Contribute> getContributeReversed(User user) {
        List<Contribute> contributeDocs = user.getContributeDocs();
        Collections.reverse(contributeDocs);
        return contributeDocs;
    }
}
