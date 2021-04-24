package indi.xezzon.school.auth.repository;

import indi.xezzon.school.common.model.Department;
import indi.xezzon.school.common.repository.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xezzon
 */
@Repository
public interface DepartmentMapper extends BaseMapper<Department> {
    /**
     * 查询部门
     *
     * @param department 参数
     * @param pageNum    页码
     * @param pageSize   页大小
     * @param orderBy    排序依据
     * @param desc       降序
     * @return 部门
     */
    @Override
    List<Department> query(@Param("t") Department department, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("orderBy") String orderBy, @Param("desc") Boolean desc);
}
