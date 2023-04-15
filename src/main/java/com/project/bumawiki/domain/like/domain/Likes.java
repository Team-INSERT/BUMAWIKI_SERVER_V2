package com.project.bumawiki.domain.like.domain;

import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.like.exception.AlreadyLikeException;
import com.project.bumawiki.domain.like.exception.YouDontLikeThisDocs;
import com.project.bumawiki.domain.user.entity.User;
import lombok.Builder;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@EqualsAndHashCode(exclude = {"id"})
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "likes_id")
    private Long id;

    @OneToMany(mappedBy = "likes", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Like> likes = new ArrayList<>();


    public void cancelLike(Like like) {
        boolean removed = likes.removeIf(savedLike -> savedLike.equals(like));

        if (!removed){
            throw YouDontLikeThisDocs.EXCEPTION;
        }
    }

    public boolean doesUserLike(User user) {
        return likes
                .stream()
                .anyMatch(like -> like.doesUserLikes(user));
    }

    public boolean doYouLike(Docs docs) {
        return likes
                .stream()
                .anyMatch(like -> like.doYouLike(docs));
    }

    public void addLike(Like like) {
        if (likes.contains(like)) {
            throw AlreadyLikeException.EXCEPTION;
        }
        this.likes.add(like);
    }
}
