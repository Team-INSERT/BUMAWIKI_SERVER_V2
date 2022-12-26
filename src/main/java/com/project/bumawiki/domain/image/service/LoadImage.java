package com.project.bumawiki.domain.image.service;


import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.MalformedURLException;


@Service
@RequiredArgsConstructor
@Transactional
public class LoadImage {

    public ResponseEntity<UrlResource> getImageByName(@PathVariable("fileName") String fileName) throws MalformedURLException {
        String imageRoot = "D:\\image\\";
        String contentDisposition = "attachment; filename=\"" + fileName + "\"";
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .header("Content-type","image/"+fileName.split("\\.")[1])
                .body(new UrlResource("file:" + imageRoot + fileName));
    }
}
