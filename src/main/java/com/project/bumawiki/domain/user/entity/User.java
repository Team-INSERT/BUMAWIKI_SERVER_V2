package com.project.bumawiki.domain.user.entity;

import com.project.bumawiki.domain.contribute.domain.Contribute;
import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.thumbsUp.domain.ThumbsUp;
import com.project.bumawiki.domain.thumbsUp.domain.ThumbsUps;
import com.project.bumawiki.domain.user.entity.authority.Authority;
import leehj050211.bsmOauth.dto.response.BsmResourceResponse;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Builder.Default
    private ThumbsUps thumbsUps = new ThumbsUps();

    public User update(BsmResourceResponse resource) {
        this.email = resource.getEmail();
        this.name = resource.getStudent().getName();
        this.enroll = resource.getStudent().getEnrolledAt();
        this.nickName = resource.getNickname();
        return this;
    }

    public void changeUserAuthority(Authority authority) {
        this.authority = authority;
    }

    public void setContributeDocs(List<Contribute> contribute) {
        this.contributeDocs = contribute;
    }

    public void addThumbsUp(ThumbsUp thumbsUp) {
        thumbsUps.addThumbsUp(thumbsUp);
    }

    public void firstThumbsUp(ThumbsUps thumbsUps) {
        this.thumbsUps = thumbsUps;
    }

    public boolean doYouLike(Docs docs) {
        return thumbsUps.doYouLike(docs);
    }

    public void thumbsUp(ThumbsUp thumbsUp) {
        thumbsUps.cancelLike(thumbsUp);
    }


}
