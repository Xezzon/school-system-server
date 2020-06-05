package indi.xezzon.school.passport.controller;

import indi.xezzon.school.common.model.Account;
import indi.xezzon.school.passport.service.AuthenticationService;
import lombok.Data;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @ResponseStatus (HttpStatus.CREATED)
    public void register(@RequestBody Account account) {
        service.register(account);
    }
    
    @PostMapping ("/login")
    public String login(@RequestBody LoginVO loginVO) {
        service.login(loginVO.getUsername(), loginVO.getCipher(), loginVO.getRememberMe());
        return (String)SecurityUtils.getSubject().getSession().getId();
    }
    
    @GetMapping ("/logout")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void logout() {
        service.logout();
    }
    
    @PutMapping ("/cipher")
    @ResponseStatus (HttpStatus.NO_CONTENT)
    public void modifyCipher(@RequestBody String cipher) {
        service.modifyCipher(cipher);
    }
}

/**
 * 作为login函数的接收参数
 *
 * @author xezzon
 */
@Data
class LoginVO {
    private String username;
    private String cipher;
    private Boolean rememberMe;
}