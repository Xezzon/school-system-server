package indi.xezzon.school.jwc.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author xezzon
 */
@FeignClient("school-auth")
public interface FeignAuthService {

    /**
     * 获取当前帐号ID
     *
     * @return 当前帐号ID
     */
    @GetMapping("/account/getCurrentAccountId")
    long getCurrentAccountId();
}
