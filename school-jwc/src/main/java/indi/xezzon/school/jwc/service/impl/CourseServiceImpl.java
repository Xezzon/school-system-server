package indi.xezzon.school.jwc.service.impl;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.EnumUtil;
import indi.xezzon.school.common.model.Course;
import indi.xezzon.school.common.model.PagedDTO;
import indi.xezzon.school.jwc.constant.enums.ElectCourseStatusEnum;
import indi.xezzon.school.jwc.repository.CourseMapper;
import indi.xezzon.school.jwc.repository.CourseStudentRelMapper;
import indi.xezzon.school.jwc.service.CourseService;
import indi.xezzon.school.jwc.service.FeignAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author xezzon
 */
@Service
public class CourseServiceImpl implements CourseService {
    private final CourseMapper courseMapper;
    private final FeignAuthService authService;
    private final RedisTemplate<String, Serializable> redisTemplate;

    private final HttpSession session;
    private final Map<ElectCourseStatusEnum, ElectCourseHandler> electCourseHandlers;

    @Autowired
    public CourseServiceImpl(CourseMapper courseMapper, FeignAuthService authService, RedisTemplate<String, Serializable> redisTemplate, HttpSession session, PreselectCourseHandler preselectCourseHandler, FreedomElectCourseHandler freedomElectCourseHandler) {
        this.courseMapper = courseMapper;
        this.authService = authService;
        this.redisTemplate = redisTemplate;
        this.session = session;

        this.electCourseHandlers = Map.ofEntries(
                Map.entry(ElectCourseStatusEnum.PRESELECTION, preselectCourseHandler),
                Map.entry(ElectCourseStatusEnum.FLASH, new FlashCourseHandler()),
                Map.entry(ElectCourseStatusEnum.FREEDOM, freedomElectCourseHandler)
        );
    }

    @Override
    public PagedDTO<Course> getCoursesPaged(int pageNum, int pageSize) {
        int total = courseMapper.count(null);
        List<Course> courses = ((pageNum - 1) * pageSize < total) ? courseMapper.query(new Course(), (pageNum - 1) * pageSize, pageSize, null, null) : ListUtil.empty();
        ElectCourseHandler electCourseHandler = electCourseHandlers.get(EnumUtil.getEnumMap(ElectCourseStatusEnum.class).get((String) redisTemplate.opsForValue().get("context:status:elect-course")));
        courses.parallelStream().forEach(course -> course.setPopulation(electCourseHandler.queryPopulation(course.getId())));
        return new PagedDTO<>(total, pageNum, pageSize, courses);
    }

    @Override
    public void electCourse(long courseId) {
        long studentId = authService.getCurrentAccountId(session.getId());
        // 从Redis获取当前选课状态并转换为Enum
        ElectCourseHandler electCourseHandler = electCourseHandlers.get(EnumUtil.getEnumMap(ElectCourseStatusEnum.class).get((String) redisTemplate.opsForValue().get("context:status:elect-course")));
        electCourseHandler.electCourse(courseId, studentId);
    }

    @Override
    public void cancelElectCourse(long courseId) {
        long studentId = authService.getCurrentAccountId(session.getId());
        ElectCourseHandler electCourseHandler = electCourseHandlers.get(EnumUtil.getEnumMap(ElectCourseStatusEnum.class).get((String) redisTemplate.opsForValue().get("context:status:elect-course")));
        electCourseHandler.cancelElectCourse(courseId, studentId);
    }
}

/**
 * 策略模式
 * 不同时段对选课和退选课使用不同的策略
 */
interface ElectCourseHandler {
    /**
     * 选课
     *
     * @param courseId  课程ID
     * @param studentId 学生ID
     */
    void electCourse(long courseId, long studentId);

    /**
     * 退选课
     *
     * @param courseId  课程ID
     * @param studentId 学生ID
     */
    void cancelElectCourse(long courseId, long studentId);

    /**
     * 查询指定课程的选课人数
     *
     * @param courseId 查询的课程
     * @return 课程的人数
     */
    Long queryPopulation(long courseId);
}

/**
 * 预选阶段
 */
@Component
class PreselectCourseHandler implements ElectCourseHandler {
    private final RedisTemplate<String, Serializable> redisTemplate;
    private final String coursePrefix;
    private final String studentPrefix;

    @Autowired
    PreselectCourseHandler(RedisTemplate<String, Serializable> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.coursePrefix = "school-jwc:preselect-course:course:";
        this.studentPrefix = "school-jwc:preselect-course:student:";
    }

    @Override
    public void electCourse(long courseId, long studentId) {
        redisTemplate.opsForSet().add(coursePrefix + courseId, studentId);
        redisTemplate.opsForSet().add(studentPrefix + studentId, courseId);
    }

    @Override
    public void cancelElectCourse(long courseId, long studentId) {
        redisTemplate.opsForSet().remove(coursePrefix + courseId, studentId);
        redisTemplate.opsForSet().remove(studentPrefix + studentId, courseId);
    }

    @Override
    public Long queryPopulation(long courseId) {
        return redisTemplate.opsForSet().size(coursePrefix + courseId);
    }
}

/**
 * TODO: 秒杀阶段
 */
class FlashCourseHandler implements ElectCourseHandler {

    @Override
    public void electCourse(long courseId, long studentId) {

    }

    /**
     * 秒杀阶段不允许退选课，故该函数为空函数
     *
     * @param courseId  课程ID
     * @param studentId 学生ID
     */
    @Override
    public void cancelElectCourse(long courseId, long studentId) {
    }

    @Override
    public Long queryPopulation(long courseId) {
        return null;
    }
}

/**
 * 自由选课阶段
 */
@Component
class FreedomElectCourseHandler implements ElectCourseHandler {
    private final CourseStudentRelMapper courseStudentRelMapper;

    FreedomElectCourseHandler(CourseStudentRelMapper courseStudentRelMapper) {
        this.courseStudentRelMapper = courseStudentRelMapper;
    }

    /**
     * 自由选课阶段直接进行数据库操作。
     * 进行选课时要判断是否超选，即选课人数超过可选人数。
     *
     * @param courseId  课程ID
     * @param studentId 学生ID
     */
    @Override
    public void electCourse(long courseId, long studentId) {
        courseStudentRelMapper.insert(studentId, courseId);
    }

    @Override
    public void cancelElectCourse(long courseId, long studentId) {
        courseStudentRelMapper.delete(studentId, courseId);
    }

    @Override
    public Long queryPopulation(long courseId) {
        return (long) courseStudentRelMapper.count(courseId);
    }
}
