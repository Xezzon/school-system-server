package indi.xezzon.school.jwc.service;

import indi.xezzon.school.common.model.Course;
import indi.xezzon.school.common.model.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CourseServiceTest {
    @Autowired
    private CourseService service;

    @Test
    public void getAllCourses() {
        PageResult<Course> courses = service.getCoursesPaged(1, 20);
        log.debug("{}", courses);
    }

    @Test
    public void electCourse() {
        service.electCourse(1);
    }

    @Test
    public void cancelElectCourse() {
        service.cancelElectCourse(1);
    }
}