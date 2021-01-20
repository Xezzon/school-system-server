package indi.xezzon.school.jwc.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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
}
