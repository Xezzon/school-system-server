package indi.xezzon.school.passport.service;

import com.github.pagehelper.PageInfo;
import indi.xezzon.school.passport.model.Role;

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
    PageInfo<Role> listRole(int page, int pageSize);
    
    /**
     * 添加一种角色及其权限
     *
     * @param role 角色信息
     */
    void addRole(Role role);
}
