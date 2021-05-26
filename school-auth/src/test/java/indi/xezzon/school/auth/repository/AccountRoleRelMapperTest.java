package indi.xezzon.school.auth.repository;

import indi.xezzon.school.common.model.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

@SpringBootTest
@Slf4j
public class AccountRoleRelMapperTest {
    @Autowired
    private AccountRoleRelMapper mapper;

    @Test
    void queryRoleByAccountId() {
        Set<Role> roles = mapper.queryRoleByAccountId(13L);
        log.debug("{}", roles);
    }
}