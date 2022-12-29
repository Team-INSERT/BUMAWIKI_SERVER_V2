package com.project.bumawiki.domain.user.entity;

import com.project.bumawiki.domain.contribute.domain.Contribute;
import com.project.bumawiki.domain.user.entity.authority.Authority;
import leehj050211.bsmOauth.dto.response.BsmResourceResponse;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true, length = 32)
    private String email;

    @Column(length = 16)
    private String nickName;

    @Enumerated(EnumType.STRING)
    @Column(length = 16)
    private Authority authority;

    @OneToMany(mappedBy = "contributor", cascade = CascadeType.ALL)
    private List<Contribute> contributeDocs = new ArrayList<>();

    public User update(BsmResourceResponse resource){
        this.email = resource.getEmail();
        this.nickName = resource.getNickname();
        return this;
    }


    public void setContributeDocs(List<Contribute> contribute){
        this.contributeDocs = contribute;
    }
}
