package com.samill.missionary_backend.participation.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ParticipantCountRepository {
    private final RedisTemplate<String, String> redisTemplate;
    public Long increment(String missionaryId) {
        return redisTemplate
                .opsForValue()
                .increment(missionaryId);
    }

    public void set(String missionaryId, String maxCount) {
        redisTemplate
                .opsForValue()
                .set(missionaryId, maxCount);
    }

    public Long decrement(String missionaryId) {
        return redisTemplate
                .opsForValue()
                .decrement(missionaryId);
    }

    public Integer get(String missionaryId) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(missionaryId))
                .map(Integer::parseInt)
                .orElse(0);
    }
}
