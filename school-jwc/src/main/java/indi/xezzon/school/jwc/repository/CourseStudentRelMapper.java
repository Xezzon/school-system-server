package indi.xezzon.school.jwc.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author xezzon
 */
@Repository
public interface CourseStudentRelMapper {
    /**
     * 选课
     *
     * @param studentId 学生ID,即账号ID
     * @param courseId  课程ID
     */
    void insert(@Param("studentId") long studentId, @Param("courseId") long courseId);

    /**
     * 退选
     *
     * @param studentId 学生ID,即账号ID
     * @param courseId  课程ID
     */
    void delete(@Param("studentId") long studentId, @Param("courseId") long courseId);
}
