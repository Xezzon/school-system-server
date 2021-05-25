package indi.xezzon.school.eams.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.io.Serializable;

/**
 * @author xezzon
 */
@FeignClient("school-auth")
public interface FeignAuthService {

    /**
     * 获取当前帐号ID
     *
     * @param sessionId sessionID
     * @return 当前帐号ID
     */
    @GetMapping("/account/getCurrentAccountId")
    long getCurrentAccountId(@RequestHeader("X-Auth-Token") Serializable sessionId);
}
