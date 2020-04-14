package indi.xezzon.school.service;

import cn.hutool.core.util.RandomUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class AuthenticationServiceTest {
    @Autowired
    private AuthenticationService service;
    
    @Test
    void register() {
        String text = RandomUtil.randomString(12);
        service.register(text, text);
    }
}