package indi.xezzon.school.auth.env;

import indi.xezzon.school.common.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.Serializable;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class RedisTest {
    @Resource(name = "redisTemplate")
    private RedisTemplate<Serializable, Serializable> redisTemplate;

    @Test
    public void create() {
        Serializable sessionId = 17L;
        Account account = new Account("xezzon", "test001");
        redisTemplate.opsForValue().set(sessionId, account);
    }

    @Test
    public void read() {
        Account account = (Account) redisTemplate.opsForValue().get(17L);
        log.debug("account:{}", account);
    }
}
