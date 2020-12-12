package indi.xezzon.school.jwc.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xezzon
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @PostMapping("/register")
    public void register(String username, String cipher) {

    }
}
