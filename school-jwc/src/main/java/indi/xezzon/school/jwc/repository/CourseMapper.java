package indi.xezzon.school.jwc.repository;

import indi.xezzon.school.common.model.Course;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xezzon
 */
@Repository
public interface CourseMapper {
    List<Course> selectList();
}
