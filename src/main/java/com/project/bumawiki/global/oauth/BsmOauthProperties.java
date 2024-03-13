package com.project.bumawiki.global.oauth;

import leehj050211.bsmOauth.BsmOauth;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "bsm")
public class BsmOauthProperties {

    private final String client_id;
    private final String secret_key;

    @Bean("bsmOauth")
    public BsmOauth bsmOauth() {
        return new BsmOauth(client_id, secret_key);
    }
}
