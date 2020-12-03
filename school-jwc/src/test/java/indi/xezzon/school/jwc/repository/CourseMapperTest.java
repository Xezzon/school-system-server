package indi.xezzon.school.jwc.repository;

import indi.xezzon.school.common.model.Course;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CourseMapperTest {
    @Autowired
    private CourseMapper mapper;

    @Test
    public void selectList() {
        List<Course> courses = mapper.selectList();
        log.debug(courses.toString());
    }
}