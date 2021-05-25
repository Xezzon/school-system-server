package indi.xezzon.school.eams.service;

import indi.xezzon.school.common.model.Course;
import indi.xezzon.school.common.model.PagedDTO;

/**
 * @author xezzon
 */
public interface CourseService {
    /**
     * 分页查询课程
     *
     * @param pageNum  页码
     * @param pageSize 每页条数
     * @return 课程列表
     */
    PagedDTO<Course> getCoursesPaged(int pageNum, int pageSize);

    /**
     * 选课
     *
     * @param courseId 课程ID
     */
    void electCourse(long courseId);

    /**
     * 退选课
     *
     * @param courseId 课程ID
     */
    void cancelElectCourse(long courseId);
}
