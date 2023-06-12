package com.project.bumawiki.domain.docs.presentation;

import com.project.bumawiki.domain.docs.service.DocsCheckYouLikeThisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/docs/like")
public class DocsCheckYouLikeThisController {
    private final DocsCheckYouLikeThisService docsCheckYouLikeThisService;

    @GetMapping("/{docsId}")
    public ResponseEntity<Boolean> checkYouLikeThis(@PathVariable Long docsId) {
        return ResponseEntity.ok(docsCheckYouLikeThisService.checkUserLikeThisDocs(docsId));
    }
}
