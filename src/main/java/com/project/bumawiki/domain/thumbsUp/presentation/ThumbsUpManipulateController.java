package com.project.bumawiki.domain.thumbsUp.presentation;

import com.project.bumawiki.domain.thumbsUp.presentation.dto.ThumbsUpRequestDto;
import com.project.bumawiki.domain.thumbsUp.service.ThumbsUpManipulateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/thumbs/up")
@RequiredArgsConstructor
public class ThumbsUpManipulateController {

    private final ThumbsUpManipulateService likesService;

    @PostMapping("/create")
    public ResponseEntity<Object> createLike(@RequestBody ThumbsUpRequestDto likeRequestDto) {
        likesService.createDocsThumbsUp(likeRequestDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> removeLike(@RequestBody ThumbsUpRequestDto likeRequestDto) {
        likesService.removeLike(likeRequestDto);

        return ResponseEntity.ok().build();
    }
}
