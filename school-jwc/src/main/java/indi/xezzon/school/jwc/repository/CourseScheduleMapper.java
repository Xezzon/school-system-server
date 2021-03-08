package indi.xezzon.school.jwc.repository;

import indi.xezzon.school.common.model.CourseSchedule;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xezzon
 */
@Repository
public interface CourseScheduleMapper {

    /**
     * 查询指定课程的课程表
     *
     * @param courseId 课程ID
     * @return 该课程的课程表
     */
    List<CourseSchedule> select(@Param("courseId") long courseId);
}
