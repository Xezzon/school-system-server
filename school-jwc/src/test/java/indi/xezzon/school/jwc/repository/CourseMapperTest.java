package indi.xezzon.school.jwc.repository;

import indi.xezzon.school.common.model.Course;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
public class CourseMapperTest {
    @Autowired
    private CourseMapper mapper;

    @Test
    public void list() {
        List<Course> courses = mapper.query(new Course(), 0, 20, "course.name", null);
        log.debug("{}", courses);
        Assertions.assertNotNull(courses.get(0).getSchedules());
    }

    @Test
    public void count() {
        int coursesCount = mapper.count(null);
        log.debug("{}", coursesCount);
    }
}