package indi.xezzon.school.auth.repository;

import indi.xezzon.school.common.model.Department;
import indi.xezzon.school.common.model.Permission;
import indi.xezzon.school.common.model.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author xezzon
 */
@Repository
public interface RolePermissionMapper {

    /**
     * 查询角色的权限
     *
     * @param role 角色
     * @return 权限集合
     */
    Set<Permission> queryPermissionByRole(@Param("role") Role role);

    /**
     * 查询部门权限
     *
     * @param department 部门
     * @return 部门权限
     */
    Set<Permission> queryPermissionByDepartment(@NotNull @Param("department") Department department);
}
