package indi.xezzon.school.jwc.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import indi.xezzon.school.common.model.Course;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xezzon
 */
@Repository
public interface CourseMapper extends BaseMapper<Course> {
    List<Course> selectList();
}
