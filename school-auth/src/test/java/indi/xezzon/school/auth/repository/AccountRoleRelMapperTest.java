package indi.xezzon.school.auth.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class AccountRoleRelMapperTest {
    @Autowired
    private AccountRoleRelMapper mapper;

    @Test
    public void insert() {
        mapper.insert(1L, 1L);
    }
}