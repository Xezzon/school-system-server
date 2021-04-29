package indi.xezzon.school.jwc.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;

@SpringBootTest
@Slf4j
class RedisConfigTest {
    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Test
    void redisTemplate() {
        Serializable hello = redisTemplate.opsForValue().get("world");
        log.debug("{}", hello);
    }
}