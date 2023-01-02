package com.project.bumawiki.domain.image.presentation;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@ConfigurationProperties(prefix = "image")
public class ImageStorageProperties {
    private String path;
    public ImageStorageProperties(String path) {
        this.path = path;
    }
}
