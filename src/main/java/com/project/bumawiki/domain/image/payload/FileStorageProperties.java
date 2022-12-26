package com.project.bumawiki.domain.image.payload;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "image")
public class FileStorageProperties {
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}