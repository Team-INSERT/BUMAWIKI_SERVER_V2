package com.project.bumawiki.domain.user.entity;

import com.project.bumawiki.domain.contribute.domain.Contribute;
import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.thumbsUp.domain.ThumbsUp;
import com.project.bumawiki.domain.thumbsUp.domain.collection.UserThumbsUps;
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

    @Embedded
    @Builder.Default
    private UserThumbsUps userThumbsUps = new UserThumbsUps();

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
        userThumbsUps.addThumbsUp(thumbsUp);

    }

    public boolean doYouLike(Docs docs) {
        return userThumbsUps.doYouThumbsUp(docs);
    }

    public void cancelThumbsUp(ThumbsUp thumbsUp) {
        userThumbsUps.cancelLike(thumbsUp);
    }
}
