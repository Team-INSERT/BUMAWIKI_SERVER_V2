package com.project.bumawiki.domain.user.entity;

import com.project.bumawiki.domain.contribute.domain.Contribute;
import com.project.bumawiki.domain.user.entity.authority.Authority;
import leehj050211.bsmOauth.dto.response.BsmResourceResponse;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true, length = 32)
    private String email;

    @Column(length = 16)
    private String name;

    @Column(length = 8)
    private Integer enroll;

    @Column(length = 16)
    private String nickName;

    @Enumerated(EnumType.STRING)
    @Column(length = 16)
    private Authority authority;

    @OneToMany(mappedBy = "contributor", cascade = CascadeType.ALL)
    private List<Contribute> contributeDocs = new ArrayList<>();

    @Builder
    private User(final String email, final String name, final Integer enroll,
                   final String nickName, final Authority authority){
        this.email = email;
        this.name = name;
        this.enroll = enroll;
        this.nickName = nickName;
        this.authority = authority;
    }

    public User update(BsmResourceResponse resource){
        this.email = resource.getEmail();
        this.name = resource.getStudent().getName();
        this.enroll = resource.getStudent().getEnrolledAt();
        this.nickName = resource.getNickname();
        return this;
    }

    public void changeUserAuthority(Authority authority){
        this.authority = authority;
    }

    public void setContributeDocs(List<Contribute> contribute){
        this.contributeDocs = contribute;
    }
}
