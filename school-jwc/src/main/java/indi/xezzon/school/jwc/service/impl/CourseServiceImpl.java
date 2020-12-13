package indi.xezzon.school.jwc.service.impl;

import indi.xezzon.school.common.model.Course;
import indi.xezzon.school.common.model.PageResult;
import indi.xezzon.school.jwc.repository.CourseMapper;
import indi.xezzon.school.jwc.repository.CourseStudentRelMapper;
import indi.xezzon.school.jwc.service.AuthenticationService;
import indi.xezzon.school.jwc.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xezzon
 */
@Service
public class CourseServiceImpl implements CourseService {
    private final CourseMapper courseMapper;
    private final CourseStudentRelMapper courseStudentRelMapper;

    private final AuthenticationService authenticationService;

    @Autowired
    public CourseServiceImpl(CourseMapper courseMapper, CourseStudentRelMapper courseStudentRelMapper, AuthenticationService authenticationService) {
        this.courseMapper = courseMapper;
        this.courseStudentRelMapper = courseStudentRelMapper;
        this.authenticationService = authenticationService;
    }

    @Override
    public PageResult<Course> getCoursesPaged(int pageNum, int pageSize) {
        int total = courseMapper.count();
        List<Course> courses = ((pageNum - 1) * pageSize < total) ? courseMapper.list((pageNum - 1) * pageSize, pageSize) : null;
        PageResult<Course> ret = new PageResult<>();
        ret.setTotal(total).setPageNum(pageNum).setPageSize(pageSize).setItems(courses);
        return ret;
    }

    @Override
    public void electCourse(long courseId) {
        long studentId = authenticationService.getCurrentAccountId();
        courseStudentRelMapper.insert(studentId, courseId);
    }

    @Override
    public void cancelElectCourse(long courseId) {
        long studentId = authenticationService.getCurrentAccountId();
        courseStudentRelMapper.delete(studentId, courseId);
    }
}
