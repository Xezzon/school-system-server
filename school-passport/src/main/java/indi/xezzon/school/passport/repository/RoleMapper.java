package indi.xezzon.school.passport.repository;

import indi.xezzon.school.passport.model.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author xezzon
 */
@Repository
public interface RoleMapper {
    /**
     * 删除一种角色
     *
     * @param id 角色id
     * @return 修改的记录数
     */
    int deleteByPrimaryKey(Integer id);
    
    /**
     * 新增一种角色
     *
     * @param record 角色
     * @return 新增记录的主键
     */
    int insert(Role record);
    
    /**
     * 修改一种角色
     *
     * @param record 角色
     * @return 修改的记录数
     */
    int updateByPrimaryKeySelective(Role record);
    
    /**
     * 列出所有角色
     *
     * @return 所有角色
     */
    List<Role> list();
    
    /* 以上方法对应role表操作 */
    /* 以下方法对应account_role_rel表操作 */
    
    /**
     * 查询用户对应的所有角色
     *
     * @param accountId 查询的用户
     * @return 角色集合
     */
    Set<Role> selectByAccountId(Long accountId);
    
    /**
     * 添加用户的角色
     *
     * @param accountId 用户
     * @param rolesId   角色
     * @return 修改的记录数
     */
    int insertAccountRoleRel(@Param ("accountId") Long accountId, @Param ("rolesId") Set<Integer> rolesId);
    
    /**
     * 删除用户的角色
     *
     * @param accountId 用户
     * @param roleId    角色
     * @return 修改的记录数
     */
    int deleteAccountRoleRel(@Param ("accountId") Long accountId, @Param ("roleId") Integer roleId);
    
    /**
     * TODO:修改用户的角色
     *
     * @return 修改的记录数
     */
    int updateAccountRoleRel();
}