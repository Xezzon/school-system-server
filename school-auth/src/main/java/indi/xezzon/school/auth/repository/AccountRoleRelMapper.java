package indi.xezzon.school.auth.repository;

import indi.xezzon.school.common.model.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author xezzon
 */
@Repository
public interface AccountRoleRelMapper {
    /**
     * 为帐号赋予权限
     *
     * @param accountId 帐号ID
     * @param roleId    角色ID
     */
    void insert(@Param("accountId") long accountId, @Param("roleId") long roleId);

    /**
     * 查询帐号的角色
     *
     * @param accountId 帐号ID
     * @return 角色列表
     */
    Set<Role> queryRoleByAccountId(@Param("accountId") Long accountId);
}
