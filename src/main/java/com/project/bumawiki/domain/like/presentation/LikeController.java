package com.project.bumawiki.domain.like.presentation;

import com.project.bumawiki.domain.like.presentation.dto.LikeRequestDto;
import com.project.bumawiki.domain.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/like")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likesService;

    @PostMapping("/create")
    public void createLike(@RequestBody @Valid LikeRequestDto likeRequestDto) {
        likesService.createDocsLike(likeRequestDto);
    }

    @DeleteMapping("/delete")
    public void removeLike(@RequestBody @Valid LikeRequestDto likeRequestDto) {
        likesService.removeLike(likeRequestDto);
    }
}
