package com.project.bumawiki.global.jwt.properties;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import javax.crypto.SecretKey;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@Getter
@ConfigurationProperties(prefix = "auth.jwt")
public class JwtProperties {
    private final String header;
    private final SecretKey secret;
    private final Long accessExp;
    private final Long refreshExp;
    private final String prefix;

    @ConstructorBinding
    public JwtProperties(String header, String secret, Long accessExp, Long refreshExp, String prefix) {
        this.header = header;
        this.secret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));;
        this.accessExp = accessExp;
        this.refreshExp = refreshExp;
        this.prefix = prefix;
    }
}
