package indi.xezzon.school.jwc.service.impl;

import cn.hutool.core.map.MapBuilder;
import cn.hutool.core.util.EnumUtil;
import indi.xezzon.school.common.model.Course;
import indi.xezzon.school.common.model.PageResult;
import indi.xezzon.school.jwc.constant.enums.ElectCourseStatusEnum;
import indi.xezzon.school.jwc.repository.CourseMapper;
import indi.xezzon.school.jwc.service.CourseService;
import indi.xezzon.school.jwc.service.FeignAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
    @Autowired
    private HttpSession session;
    private final Map<ElectCourseStatusEnum, ElectCourseHandler> electCourseHandlers;
    @Autowired
    private final RedisTemplate<String, Serializable> redisTemplate;

    @Autowired
    public CourseServiceImpl(CourseMapper courseMapper, FeignAuthService authService, RedisTemplate<String, Serializable> redisTemplate) {
        this.courseMapper = courseMapper;
        this.authService = authService;
        this.redisTemplate = redisTemplate;
        this.electCourseHandlers = MapBuilder.<ElectCourseStatusEnum, ElectCourseHandler>create()
                .map();
    }

    @Override
    public PageResult<Course> getCoursesPaged(int pageNum, int pageSize) {
        int total = courseMapper.count();
        List<Course> courses = ((pageNum - 1) * pageSize < total) ? courseMapper.list((pageNum - 1) * pageSize, pageSize) : null;
        PageResult<Course> ret = new PageResult<>();
        ret.setTotal(total).setPageNum(pageNum).setPageSize(pageSize).setItems(courses);
        return ret;
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
}
