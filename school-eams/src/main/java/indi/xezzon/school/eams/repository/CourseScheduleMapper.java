package indi.xezzon.school.eams.repository;

import indi.xezzon.school.common.model.CourseSchedule;
import indi.xezzon.school.common.repository.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xezzon
 */
@Repository
public interface CourseScheduleMapper extends BaseMapper<CourseSchedule> {
    /**
     * 分页获取课程表
     *
     * @param param    参数
     * @param pageNum  页码
     * @param pageSize 页大小
     * @param orderBy 排序依据
     * @param desc 降序
     * @return 课程表
     */
    @Override
    List<CourseSchedule> query(@Param("t") CourseSchedule param, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("orderBy") String orderBy, @Param("desc") Boolean desc);

    /**
     * 查询课程的课程表
     *
     * @param courseId 课程ID
     * @return 课程表
     */
    List<CourseSchedule> queryByCourseId(@Param("courseId") long courseId);
}
