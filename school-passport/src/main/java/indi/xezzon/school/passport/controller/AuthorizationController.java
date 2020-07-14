package indi.xezzon.school.passport.controller;

import indi.xezzon.school.common.model.PageResult;
import indi.xezzon.school.common.model.Permission;
import indi.xezzon.school.common.model.Role;
import indi.xezzon.school.passport.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     * 分页查询所有角色
     */
    @GetMapping ("/roles")
    public PageResult<Role> listRole(@RequestParam ("page") int page, @RequestParam ("pageSize") int pageSize) {
        PageResult<Role> result = new PageResult<>();
        result.setItems(service.listRole(page, pageSize));
        return result;
    }
    
    /**
     * 列出所有权限
     */
    @GetMapping ("/permissions")
    public List<Permission> listPermission() {
        return service.listPermission();
    }
    
    /**
     * 添加一种角色及其对应的权限
     */
    @PostMapping ("/role")
    public void addRole(Role role) {
        service.addRole(role);
    }
}
