package com.project.bumawiki.domain.thumbsUp.presentation;

import com.project.bumawiki.domain.thumbsUp.presentation.dto.ThumbsUpResponseDto;
import com.project.bumawiki.domain.thumbsUp.service.ThumbsUpInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/thumbs/up")
@RestController
public class ThumbsUpInformationController {
    private final ThumbsUpInformationService thumbsUpInformationService;

    @GetMapping("/get")
    public List<ThumbsUpResponseDto> getThumbsUp() {
        return thumbsUpInformationService.getThumbsUpList();
    }
}
