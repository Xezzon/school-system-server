package indi.xezzon.school.auth.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.io.Serializable;

@SpringBootTest
@Slf4j
class RedisConfigTest {
    @Resource
    private RedisTemplate<String, Serializable> redisTemplate;

    @Test
    void redisTemplate() {
        Serializable hello = redisTemplate.opsForValue().get("world");
        log.debug("{}", hello);
    }
}