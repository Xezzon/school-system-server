package indi.xezzon.school.jwc.repository;

import indi.xezzon.school.common.model.CourseSchedule;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class CourseScheduleMapperTest {
    @Autowired
    private CourseScheduleMapper courseScheduleMapper;

    @Test
    void select() {
        List<CourseSchedule> courseSchedules = courseScheduleMapper.select(111190);
        log.debug("{}", courseSchedules);
    }
}