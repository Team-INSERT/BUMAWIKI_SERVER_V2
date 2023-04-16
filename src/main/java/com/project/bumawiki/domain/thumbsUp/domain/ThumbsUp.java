package com.project.bumawiki.domain.thumbsUp.domain;

import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(exclude = {"id"})
public class ThumbsUp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "thumbs_up_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "docs_id")
    private Docs docs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thumb_ups_id")
    private ThumbUps thumbUps;

    public boolean doesUserLikes(User user) {
        return this.user.equals(user);
    }

    public boolean doYouLike(Docs docs) {
        return this.docs.equals(docs);
    }
}
