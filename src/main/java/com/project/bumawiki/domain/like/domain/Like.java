package com.project.bumawiki.domain.like.domain;

import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "likes")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "likes")
    private Docs docs;

    public Like(User user, Docs docs) {
        this.user = user;
        this.docs = docs;
    }

    public boolean doesUserLikes(User user) {
        if (this.user.equals(user)) {
            return true;
        }

        return false;
    }
}
