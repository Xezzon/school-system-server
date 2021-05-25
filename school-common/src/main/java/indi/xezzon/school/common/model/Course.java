package indi.xezzon.school.common.model;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author xezzon
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
public class Course extends BaseEntity {
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
     * 课程类别 dict.course_type
     */
    private Integer type;

    /**
     * 课程属性
     */
    private String profile;

    /**
     * 所属学院
     */
    private Long instituteId;

    /**
     * 课程介绍
     */
    private String description;

    /**
     * 可选人数 为0时不限选课人数
     */
    private Integer containment;

    /**
     * 已选人数
     */
    private Integer population;

    /**
     * 课程表
     */
    private List<CourseSchedule> schedules;

    private static final long serialVersionUID = 1L;
}
