package indi.xezzon.school.auth.service;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
public class AuthenticationServiceTest {
    @Autowired
    private AuthenticationService service;

    @Resource
    SecurityManager securityManager;
    @BeforeEach
    public void before() {
        ThreadContext.bind(securityManager);
    }

    @Test
    public void register() {
        //service.register("test_bcrypt", "test");
        String randomString = RandomUtil.randomString(8);
        service.register(randomString, randomString, 1);
    }

    @Test
    public void login() {
        service.login("test_bcrypt", "test");
        Subject subject = SecurityUtils.getSubject();
        log.debug("{}", subject);
        assert subject.isAuthenticated();
    }

    @Test
    public void getCurrentAccountId() {
        log.debug("Current account id is: {}", service.getCurrentAccountId());
        service.login("test_bcrypt", "test");
        log.debug("Current account id is: {}", service.getCurrentAccountId());
    }
}