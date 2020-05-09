package indi.xezzon.school.passport.controller;

import indi.xezzon.school.passport.model.Role;
import indi.xezzon.school.passport.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xezzon
 */
@RestController
@RequestMapping ("/customs")
public class AuthorizationController {
    private final AuthorizationService service;
    
    @Autowired
    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }
    
    /**
     * 添加一种角色及其对应的权限
     */
    @PostMapping ("/role")
    public void addRole(Role role) {
        service.addRole(role);
    }
}
