package com.project.bumawiki.domain.image;


import com.project.bumawiki.domain.image.service.LoadImage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class ImageController {
    private final LoadImage Image;
    @GetMapping("/image/{fileName}")
    public ResponseEntity<Resource> createDocs(@PathVariable("fileName") String fileName){
        return Image.getImageByName(fileName);
    }

}
