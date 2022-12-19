package com.project.bumawiki.domain.user;

import com.project.bumawiki.domain.user.authority.Authority;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class User {
    @Id @GeneratedValue
    private Long id;

    @Column(unique = true, length = 32)
    private String authId;

    @Column(length = 32)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(length = 16)
    private Authority authority;
}
