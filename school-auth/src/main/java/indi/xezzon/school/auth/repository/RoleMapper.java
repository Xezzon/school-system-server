package indi.xezzon.school.auth.repository;

import indi.xezzon.school.common.model.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xezzon
 */
@Repository
public interface RoleMapper {
    /**
     * 查询所有角色
     *
     * @return 角色列表
     */
    List<Role> getAll();
}
