package indi.xezzon.school.auth.repository;

import indi.xezzon.school.common.model.Role;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RoleMapperTest {
    @Autowired
    private RoleMapper mapper;

    @Test
    public void getAll() {
        List<Role> roles = mapper.getAll();
        log.debug("{}", roles);
    }
}