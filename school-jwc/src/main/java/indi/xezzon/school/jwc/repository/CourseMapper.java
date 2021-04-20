package indi.xezzon.school.jwc.repository;

import indi.xezzon.school.common.model.Course;
import indi.xezzon.school.common.repository.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xezzon
 */
@Repository
public interface CourseMapper extends BaseMapper<Course> {
    /**
     * 查询所有课程
     *
     * @param param 参数
     * @param pageNum  页码
     * @param pageSize 页大小
     * @param orderBy 排序依据
     * @param desc 降序
     * @return 课程
     */
    @Override
    List<Course> query(@Param("t") Course param, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("orderBy") String orderBy, @Param("desc") Boolean desc);
}
