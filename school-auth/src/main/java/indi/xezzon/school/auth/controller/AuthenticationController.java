package indi.xezzon.school.auth.controller;

import indi.xezzon.school.auth.service.AuthenticationService;
import indi.xezzon.school.common.model.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

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
    public Serializable login(String username, String cipher) {
        return authenticationService.login(username, cipher);
    }
}

@Getter
@Setter
class RegisterParam {
    private String username;
    private String cipher;
    private Role role;
}
