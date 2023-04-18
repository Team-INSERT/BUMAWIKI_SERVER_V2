package com.project.bumawiki.domain.thumbsUp.presentation;

import com.project.bumawiki.domain.thumbsUp.presentation.dto.ThumbsUpRequestDto;
import com.project.bumawiki.domain.thumbsUp.service.ThumbsUpManipulateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/thumbs/up")
@RequiredArgsConstructor
public class ThumbsUpManipulateController {

    private final ThumbsUpManipulateService likesService;

    @PostMapping("/create")
    public void createLike(@RequestBody ThumbsUpRequestDto likeRequestDto) {
        likesService.createDocsThumbsUp(likeRequestDto);
    }

    @DeleteMapping("/delete")
    public void removeLike(@RequestBody ThumbsUpRequestDto likeRequestDto) {
        likesService.removeLike(likeRequestDto);
    }
}