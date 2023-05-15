package com.project.bumawiki.domain.thumbsUp.domain.collection;

import com.project.bumawiki.domain.docs.domain.Docs;
import com.project.bumawiki.domain.thumbsUp.domain.ThumbsUp;
import com.project.bumawiki.domain.thumbsUp.exception.AlreadyThumbsUpexception;
import com.project.bumawiki.domain.thumbsUp.exception.YouDontThumbsUpThisDocs;
import com.project.bumawiki.domain.thumbsUp.presentation.dto.ThumbsUpResponseDto;
import com.project.bumawiki.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Embeddable
public class UserThumbsUps {

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @Builder.Default
    private final List<ThumbsUp> thumbsUps = new ArrayList<>();

    public void cancelLike(ThumbsUp thumbsUp) {
        boolean removed = thumbsUps
                .removeIf(thumbsUp::equals);

        if (!removed) {
            throw YouDontThumbsUpThisDocs.EXCEPTION;
        }
    }

    public boolean doesUserThumbsUp(User user) {
        return thumbsUps
                .stream()
                .anyMatch(like -> like.doesUserThumbsUp(user));
    }

    public boolean doYouThumbsUp(Docs docs) {
        return thumbsUps
                .stream()
                .anyMatch(like -> like.doYouThumbsUp(docs));
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
