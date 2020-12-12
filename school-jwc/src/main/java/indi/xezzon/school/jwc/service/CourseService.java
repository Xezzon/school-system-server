package indi.xezzon.school.jwc.service;

import indi.xezzon.school.common.model.Course;
import indi.xezzon.school.common.model.PageResult;

/**
 * @author xezzon
 */
public interface CourseService {
    PageResult<Course> getCoursesPaged(int pageNum, int pageSize);
}
