package indi.xezzon.school.controller;

import indi.xezzon.school.model.Account;
import indi.xezzon.school.service.AuthenticationService;
import lombok.Data;
import org.apache.shiro.SecurityUtils;
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
    
    @PostMapping ("/login")
    public String login(@RequestBody LoginVO loginVO) {
        service.login(loginVO.getUsername(), loginVO.getCipher(), loginVO.getRememberMe());
        return (String)SecurityUtils.getSubject().getSession().getId();
    }
}

@Data
class LoginVO {
    private String username;
    private String cipher;
    private Boolean rememberMe;
}
