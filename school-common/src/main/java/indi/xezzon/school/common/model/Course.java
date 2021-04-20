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
     * 可选人数
     */
    private Long containment;

    /**
     * 已选人数
     */
    private Long population;

    /**
     * 课程表
     */
    private List<CourseSchedule> schedules;

    /**
     * 课程类型。1:必修课;2:选修课;
     */
    private Integer type;
}
