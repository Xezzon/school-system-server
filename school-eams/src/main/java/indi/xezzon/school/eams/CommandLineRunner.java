package indi.xezzon.school.eams;

import indi.xezzon.school.eams.constant.enums.ElectCourseStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author xezzon
 */
@Component
public class CommandLineRunner implements org.springframework.boot.CommandLineRunner {
    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Override
    public void run(String... args) throws Exception {
        redisTemplate.opsForValue().setIfAbsent("context:status:elect-course", ElectCourseStatusEnum.CLOSED);
    }
}
