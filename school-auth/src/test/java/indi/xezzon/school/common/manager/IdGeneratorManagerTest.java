package indi.xezzon.school.common.manager;

import indi.xezzon.school.auth.AuthApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = AuthApplication.class)
@Slf4j
public class IdGeneratorManagerTest {
    @Autowired
    private IdGeneratorManager manager;

    @Test
    void generateId() {
        Long accountId = manager.generateId("account");
        log.debug("accountId:{}", accountId);
    }
}
