package com.project.bumawiki.domain.user.presentation.dto;

import com.project.bumawiki.domain.contribute.domain.Contribute;
import com.project.bumawiki.domain.user.entity.User;
import com.project.bumawiki.domain.user.entity.authority.Authority;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class UserResponseDto {
    private Long id;

    private String email;

    private String nickName;

    private Authority authority;

    private List<Contribute> contributeDocs = new ArrayList<>();

    public UserResponseDto updateContribute(Contribute contribute){
        contributeDocs.add(0, contribute);
        return this;
    }

    public UserResponseDto(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.nickName = user.getNickName();
        this.authority = user.getAuthority();
    }
}
