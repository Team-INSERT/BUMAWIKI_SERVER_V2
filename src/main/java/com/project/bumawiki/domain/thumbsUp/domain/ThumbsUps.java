package com.project.bumawiki.domain.thumbsUp.domain;

import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.thumbsUp.exception.AlreadyThumbsUpexception;
import com.project.bumawiki.domain.thumbsUp.exception.YouDontThumbsUpThisDocs;
import com.project.bumawiki.domain.thumbsUp.presentation.dto.ThumbsUpResponseDto;
import com.project.bumawiki.domain.user.entity.User;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@EqualsAndHashCode(exclude = {"id"})
public class ThumbsUps {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "thumbs_ups_id")
    private Long id;

    @OneToMany(mappedBy = "thumbsUps", cascade = CascadeType.ALL)
    @Builder.Default
    private List<ThumbsUp> thumbsUps = new ArrayList<>();


    public void cancelLike(ThumbsUp thumbsUp) {
        boolean removed = thumbsUps.removeIf(savedLike -> savedLike.equals(thumbsUp));

        if (!removed) {
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

    public void addThumbsUp(ThumbsUp thumbsUp) {
        boolean anyMatch = thumbsUps
                .stream()
                .anyMatch(iterThumbsUp -> iterThumbsUp.equals(thumbsUp));

        if (anyMatch) {
            throw AlreadyThumbsUpexception.EXCEPTION;
        }
        this.thumbsUps.add(thumbsUp);
    }

    public List<ThumbsUpResponseDto> getList() {
        return this.thumbsUps
                .stream()
                .map(ThumbsUp::getDto)
                .collect(Collectors.toList());
    }

    public int getThumbsUpsCount() {
        return thumbsUps.size();
    }
}
