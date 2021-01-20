package indi.xezzon.school.auth.controller;

import indi.xezzon.school.auth.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xezzon
 */
@RestController
public class AuthRpcController {
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/account/getCurrentAccountId")
    public long getCurrentAccountId() {
        return authenticationService.getCurrentAccountId();
    }
}
