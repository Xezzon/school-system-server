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
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthRpcController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/account/getCurrentAccountId")
    public Long getCurrentAccountId() {
        return authenticationService.getCurrentAccountId();
    }
}
