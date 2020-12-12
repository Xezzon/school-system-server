package indi.xezzon.school.jwc.service;

import indi.xezzon.school.common.model.Course;
import indi.xezzon.school.common.model.PageResult;

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
    PageResult<Course> getCoursesPaged(int pageNum, int pageSize);
}
