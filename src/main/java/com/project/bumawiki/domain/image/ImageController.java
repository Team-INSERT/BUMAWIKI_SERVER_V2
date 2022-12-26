package com.project.bumawiki.domain.image;


import com.project.bumawiki.domain.image.service.LoadImage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.project.bumawiki.domain.image.exception.NoImageException;

import java.net.MalformedURLException;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class ImageController {
    private final LoadImage Image = new LoadImage();
    @GetMapping("/image/{fileName}")
    public ResponseEntity<UrlResource> GetImage(@PathVariable("fileName") String fileName) throws NoImageException, MalformedURLException {
        return Image.getImageByName(fileName);
    }

}
