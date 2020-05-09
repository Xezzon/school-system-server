package indi.xezzon.school.passport.service;

import indi.xezzon.school.passport.model.Role;

/**
 * @author xezzon
 */
public interface AuthorizationService {
    
    /**
     * 添加一种角色及其权限
     *
     * @param role 角色信息
     */
    void addRole(Role role);
}
