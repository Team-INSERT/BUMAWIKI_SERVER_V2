package com.project.bumawiki.domain.user.presentation.dto;

import com.project.bumawiki.domain.contribute.domain.Contribute;
import com.project.bumawiki.domain.contribute.domain.dto.ContributeDto;
import com.project.bumawiki.domain.user.entity.User;
import com.project.bumawiki.domain.user.entity.authority.Authority;
import lombok.Getter;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserResponseDto {
    private Long id;

    private String email;

    private String nickName;

    private Authority authority;

    private List<ContributeDto> contributeDocs;

    public UserResponseDto(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.nickName = user.getNickName();
        this.authority = user.getAuthority();
        this.contributeDocs = user.getContributeDocs()
                .stream()
                .map(ContributeDto::new)
                .collect(Collectors.toList());
    }
}
