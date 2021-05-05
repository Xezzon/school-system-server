package indi.xezzon.school.common.manager;

import indi.xezzon.school.common.constant.RedisPrefixConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author xezzon
 */
@Component
public class IdGeneratorManager {
    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    public Long generateId(String table) {
        return redisTemplate.opsForValue().increment(RedisPrefixConstant.GLOBAL_ID + table);
    }
}
