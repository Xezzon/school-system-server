package indi.xezzon.school.jwc.service;

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
        log.debug(service.getAllCourses().toString());
    }
}