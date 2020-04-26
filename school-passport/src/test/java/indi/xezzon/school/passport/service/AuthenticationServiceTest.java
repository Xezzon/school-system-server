package indi.xezzon.school.passport.service;

import cn.hutool.core.util.RandomUtil;
import indi.xezzon.school.passport.model.Account;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.util.ThreadContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationServiceTest {
    @Autowired
    private AuthenticationService service;
    
    // 解决测试环境下Shiro报错的问题，方法来源： https://www.oschina.net/question/4934_48690
    @Resource
    SecurityManager securityManager;
    @Before
    public void before() {
        ThreadContext.bind(securityManager);
    }
    
    @Test
    public void register() {
        String text = RandomUtil.randomString(12);
        Account account = new Account();
        account.setUsername(text);
        account.setCipher(text);
        service.register(account);
        assert true;
    }
    
    @Test
    public void login() {
        service.login("test", "test001", false);
        assert SecurityUtils.getSubject().isAuthenticated();
    }
}
