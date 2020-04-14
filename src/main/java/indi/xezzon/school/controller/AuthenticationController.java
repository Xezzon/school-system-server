package indi.xezzon.school.controller;

import indi.xezzon.school.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xezzon
 */
@RestController
@RequestMapping ("/passport")
public class AuthenticationController {
    private final AuthenticationService service;
    
    @Autowired
    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }
    
    @PostMapping ("/register")
    public void register(@RequestBody Account account) {
        service.register(account);
    }
}
