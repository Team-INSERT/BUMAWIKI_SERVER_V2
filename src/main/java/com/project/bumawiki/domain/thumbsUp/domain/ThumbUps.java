package com.project.bumawiki.domain.thumbsUp.domain;

import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.thumbsUp.exception.AlreadyThumbsUpexception;
import com.project.bumawiki.domain.thumbsUp.exception.YouDontThumbsUpThisDocs;
import com.project.bumawiki.domain.user.entity.User;
import lombok.Builder;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@EqualsAndHashCode(exclude = {"id"})
public class ThumbUps {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "thumb_ups_id")
    private Long id;

    @OneToMany(mappedBy = "thumbUps", cascade = CascadeType.ALL)
    @Builder.Default
    private List<ThumbsUp> thumbsUps = new ArrayList<>();


    public void cancelLike(ThumbsUp thumbsUp) {
        boolean removed = thumbsUps.removeIf(savedLike -> savedLike.equals(thumbsUp));

        if (!removed){
            throw YouDontThumbsUpThisDocs.EXCEPTION;
        }
    }

    public boolean doesUserLike(User user) {
        return thumbsUps
                .stream()
                .anyMatch(like -> like.doesUserLikes(user));
    }

    public boolean doYouLike(Docs docs) {
        return thumbsUps
                .stream()
                .anyMatch(like -> like.doYouLike(docs));
    }

    public void addLike(ThumbsUp thumbsUp) {
        if (thumbsUps.contains(thumbsUp)) {
            throw AlreadyThumbsUpexception.EXCEPTION;
        }
        this.thumbsUps.add(thumbsUp);
    }
}
