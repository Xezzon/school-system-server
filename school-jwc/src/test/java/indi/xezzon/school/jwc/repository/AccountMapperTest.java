package indi.xezzon.school.jwc.repository;

import cn.hutool.core.util.RandomUtil;
import indi.xezzon.school.common.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AccountMapperTest {
    @Autowired
    private AccountMapper mapper;

    @Test
    public void insert() {
        String randomStr = RandomUtil.randomString(8);
        Account account = new Account(randomStr, randomStr);
        Long id = mapper.insert(account);
        log.debug("New user id is:{}", id);
        assert id != null;
    }
}