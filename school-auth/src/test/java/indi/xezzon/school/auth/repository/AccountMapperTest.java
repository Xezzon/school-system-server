package indi.xezzon.school.auth.repository;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import indi.xezzon.school.common.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class AccountMapperTest {
    @Autowired
    private AccountMapper mapper;

    @Test
    public void insert() {
        String randomStr = RandomUtil.randomString(8);
        Account account = new Account(randomStr, randomStr);
        mapper.insert(account);
        log.debug("New user id is: {}", account.getId());
        assert ObjectUtil.isNotNull(account.getId());
    }

    @Test
    public void selectByUsername() {
        String username = "test001";
        Account account = mapper.queryByUsername(username);
        log.debug("{}", account);
    }
}