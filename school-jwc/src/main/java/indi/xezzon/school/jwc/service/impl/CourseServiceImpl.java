package indi.xezzon.school.jwc.service.impl;

import indi.xezzon.school.common.model.Course;
import indi.xezzon.school.common.model.PageResult;
import indi.xezzon.school.jwc.repository.CourseMapper;
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

    @Autowired
    public CourseServiceImpl(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    @Override
    public PageResult<Course> getCoursesPaged(int pageNum, int pageSize) {
        int total = courseMapper.count();
        List<Course> courses = ((pageNum - 1) * pageSize < total) ? courseMapper.list((pageNum - 1) * pageSize, pageSize) : null;
        PageResult<Course> ret = new PageResult<>();
        ret.setTotal(total).setPageNum(pageNum).setPageSize(pageSize).setItems(courses);
        return ret;
    }
}
