package com.project.bumawiki.domain.image.service;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.project.bumawiki.domain.image.exception.NoImageException;

public class LoadImage {
    public ResponseEntity<Resource> getImageByName(@PathVariable("fileName") String fileName) {
        ResponseEntity<Resource> resourceResponseEntity;
        try {
            String path = "실제 이미지가 있는 위치";
            FileSystemResource resource = new FileSystemResource(path + fileName);
            if (!resource.exists()) {
                throw NoImageException.EXCEPTION;
            }
            HttpHeaders header = new HttpHeaders();
            Path filePath;
            filePath = Paths.get(path + fileName);
            header.add("Content-Type", Files.probeContentType(filePath));
            resourceResponseEntity = new ResponseEntity<Resource>((Resource) resource, header, HttpStatus.OK);
            return resourceResponseEntity;
        } catch (Exception e) {
            throw NoImageException.EXCEPTION;
        }
    }
}
