package indi.xezzon.school.passport.controller;

import indi.xezzon.school.common.model.PageResult;
import indi.xezzon.school.passport.model.Role;
import indi.xezzon.school.passport.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * TODO:分页查询所有角色
     */
    @GetMapping (value = "/roles")
    public PageResult<Role> listRole(@RequestParam ("page") int page, @RequestParam ("pageSize") int pageSize) {
        return PageResult.from(service.listRole(page, pageSize));
    }
    
    /**
     * 添加一种角色及其对应的权限
     */
    @PostMapping ("/role")
    public void addRole(Role role) {
        service.addRole(role);
    }
}
