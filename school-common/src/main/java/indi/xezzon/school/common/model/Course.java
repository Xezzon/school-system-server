package indi.xezzon.school.common.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xezzon
 */
@Getter
@Setter
@ToString
public class Course implements Serializable {
    private Long id;

    /**
     * 课程名称
     */
    private String name;

    /**
     * 学分
     */
    private String credit;

    /**
     * 执教老师
     */
    private Teacher teacher;

    /**
     * 可选人数
     */
    private Long containment;

    /**
     * 已选人数
     */
    private Long population;

    private List<CourseSchedule> schedules;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;
}
