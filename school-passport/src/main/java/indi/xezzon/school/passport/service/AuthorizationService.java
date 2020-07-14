package indi.xezzon.school.passport.service;

import indi.xezzon.school.common.model.Permission;
import indi.xezzon.school.common.model.Role;

import java.util.List;

/**
 * @author xezzon
 */
public interface AuthorizationService {
    
    /**
     * 分页查询角色
     *
     * @param page     页码
     * @param pageSize 每页的记录数
     * @return 查询的角色
     */
    List<Role> listRole(int page, int pageSize);
    
    /**
     * 查询所有的权限
     *
     * @return 查询的权限
     */
    List<Permission> listPermission();
    
    /**
     * 添加一种角色及其权限
     *
     * @param role 角色信息
     */
    void addRole(Role role);
}
