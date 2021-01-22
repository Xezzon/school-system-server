package indi.xezzon.school.jwc.service.impl;

import indi.xezzon.school.common.model.Course;
import indi.xezzon.school.common.model.PageResult;
import indi.xezzon.school.jwc.repository.CourseMapper;
import indi.xezzon.school.jwc.repository.CourseStudentRelMapper;
import indi.xezzon.school.jwc.service.CourseService;
import indi.xezzon.school.jwc.service.FeignAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author xezzon
 */
@Service
public class CourseServiceImpl implements CourseService {
    private final CourseMapper courseMapper;
    private final CourseStudentRelMapper courseStudentRelMapper;
    private final FeignAuthService authService;
    @Autowired
    private HttpSession session;

    @Autowired
    public CourseServiceImpl(CourseMapper courseMapper, CourseStudentRelMapper courseStudentRelMapper, FeignAuthService authService) {
        this.courseMapper = courseMapper;
        this.courseStudentRelMapper = courseStudentRelMapper;
        this.authService = authService;
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
        long studentId = authService.getCurrentAccountId(session.getId());
        courseStudentRelMapper.insert(studentId, courseId);
    }

    @Override
    public void cancelElectCourse(long courseId) {
        long studentId = authService.getCurrentAccountId(session.getId());
        courseStudentRelMapper.delete(studentId, courseId);
    }
}
