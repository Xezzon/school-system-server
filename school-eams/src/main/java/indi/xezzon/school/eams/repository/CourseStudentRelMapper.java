package indi.xezzon.school.eams.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author xezzon
 */
@Repository
public interface CourseStudentRelMapper {
    /**
     * 选课
     * 选课前确保不会超选。
     *
     * @param studentId 学生ID,即账号ID
     * @param courseId  课程ID
     * @return 是否插入成功。插入成功返回1,若超选则返回0,若已选过则抛出异常。
     */
    int insert(@Param("studentId") long studentId, @Param("courseId") long courseId);

    /**
     * 退选
     *
     * @param studentId 学生ID,即账号ID
     * @param courseId  课程ID
     */
    void delete(@Param("studentId") long studentId, @Param("courseId") long courseId);

    /**
     * 查询课程的选课人数
     *
     * @param courseId 课程ID
     * @return 该课程的选课人数
     */
    int count(@Param("courseId") long courseId);
}
