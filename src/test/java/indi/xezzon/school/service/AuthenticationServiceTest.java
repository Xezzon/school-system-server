package indi.xezzon.school.service;

import cn.hutool.core.util.RandomUtil;
import indi.xezzon.school.model.Account;
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
        Account account = new Account();
        account.setUsername(text);
        account.setCipher(text);
        service.register(account);
        assert true;
    }
}