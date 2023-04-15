package com.project.bumawiki.domain.like.domain;

import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Table(name = "like")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(exclude = {"id"})
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "docs_id")
    private Docs docs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "likes_id")
    private Likes likes;

    public boolean doesUserLikes(User user) {
        return this.user.equals(user);
    }

    public boolean doYouLike(Docs docs) {
        return this.docs.equals(docs);
    }
}
