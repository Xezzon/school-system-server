package indi.xezzon.school.passport.repository;

import indi.xezzon.school.passport.model.Permission;
import indi.xezzon.school.passport.model.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author xezzon
 */
@Repository
public interface PermissionMapper {
    /**
     * 列出所有的权限
     *
     * @return 所有权限
     */
    List<Permission> list();
    
    /**
     * 查询用户的权限
     *
     * @param roles 用户的所有角色
     * @return 用户的所有权限
     */
    Set<Permission> selectByRoles(@Param ("set") Set<Role> roles);
    
    /**
     * 新增角色与权限的对应关系
     *
     * @param roleId        角色
     * @param permissionsId 权限
     * @return 修改的记录数
     */
    int insertRolePermissionRel(@Param ("roleId") Integer roleId, @Param ("permissionsId") List<Integer> permissionsId);
    
    /**
     * 减少角色的权限
     *
     * @param roleId       角色
     * @param permissionId 权限
     * @return 修改的记录数
     */
    int deleteRolePermissionRel(@Param ("roleId") Integer roleId, @Param ("permissionId") Integer permissionId);
    
    /**
     * TODO:修改角色的权限
     *
     * @return 修改的记录数
     */
    int updateRolePermissionRel();
}