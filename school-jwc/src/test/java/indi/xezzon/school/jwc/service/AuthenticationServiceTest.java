package indi.xezzon.school.jwc.service;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AuthenticationServiceTest {
    @Autowired
    private AuthenticationService service;

    @Test
    public void register() {
        String randomString = RandomUtil.randomString(8);
        service.register(randomString, randomString);
    }
}