package com.project.bumawiki.domain.auth.domain;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Id;
import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@RedisHash
public class RefreshToken {
    @Id
    private String id;
    @Indexed
    private String refreshToken;

    private String role;

    @TimeToLive
    private long ttl;

    private ZonedDateTime expiredAt;

    public RefreshToken update(String refreshToken, long ttl) {
        this.refreshToken = refreshToken;
        this.ttl = ttl;
        return this;
    }
}
