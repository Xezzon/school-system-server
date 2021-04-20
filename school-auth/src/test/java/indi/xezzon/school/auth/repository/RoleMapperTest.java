package indi.xezzon.school.auth.repository;

import indi.xezzon.school.common.model.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class RoleMapperTest {
    @Autowired
    private RoleMapper mapper;

    @Test
    public void queryAll() {
        List<Role> roles = mapper.query(new Role(), null, null, null, null);
        log.debug("{}", roles);
    }
}