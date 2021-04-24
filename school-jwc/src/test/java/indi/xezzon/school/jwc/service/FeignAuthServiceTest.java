package indi.xezzon.school.jwc.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class FeignAuthServiceTest {
    @Autowired
    private FeignAuthService authService;

    @Test
    public void getCurrentAccountId() {
        long currentAccountId = authService.getCurrentAccountId(1);
        log.debug("currentAccountId:{}", currentAccountId);
        assert currentAccountId != -1L;
    }
}