package indi.xezzon.school.jwc.repository;

import indi.xezzon.school.common.model.CourseSchedule;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class CourseScheduleMapperTest {
    @Autowired
    private CourseScheduleMapper mapper;

    @Test
    void queryByCourseId() {
        List<CourseSchedule> courseSchedules = mapper.query(CourseSchedule.builder().courseId(111190L).build(), null, null, null, null);
        log.debug("courseSchedules: {}", courseSchedules);
        List<CourseSchedule> courseScheduleList = mapper.queryByCourseId(111190L);
        log.debug("courseScheduleList: {}", courseScheduleList);
        Assertions.assertEquals(courseSchedules.size(), courseScheduleList.size());
    }

    @Test
    void count() {
        int count = mapper.count(CourseSchedule.builder().courseId(111190L).build());
        log.debug("{}", count);
    }
}