package indi.xezzon.school.jwc.repository;

import indi.xezzon.school.common.model.Course;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xezzon
 */
@Repository
public interface CourseMapper {
    List<Course> list(@Param("offset") int offset, @Param("limit") int limit);

    int count();
}
