package com.project.bumawiki.global.jwt.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "auth.jwt")
public class JwtProperties {
    private final String header;
    private final String secret;
    private final Long accessExp;
    private final Long refreshExp;
    private final String prefix;
}
