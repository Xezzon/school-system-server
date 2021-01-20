package indi.xezzon.school.jwc.controller;

import indi.xezzon.school.common.model.Role;
import indi.xezzon.school.jwc.service.AuthenticationService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xezzon
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public void register(@RequestBody RegisterParam param) {
        authenticationService.register(param.getUsername(), param.getCipher(), param.getRole().getId());
    }

    @PostMapping("/login")
    public void login(String username, String cipher) {
        authenticationService.login(username, cipher);
    }
}

@Getter
@Setter
class RegisterParam {
    private String username;
    private String cipher;
    private Role role;
}
