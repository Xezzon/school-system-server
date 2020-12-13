package indi.xezzon.school.jwc.repository;

import indi.xezzon.school.common.model.Course;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xezzon
 */
@Repository
public interface CourseMapper {
    /**
     * 分页查询课程
     *
     * @param offset 偏移值
     * @param limit  查询条数
     * @return 该分页下的所有课程
     */
    List<Course> list(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 查询课程数量
     *
     * @return 课程数量
     */
    int count();
}
