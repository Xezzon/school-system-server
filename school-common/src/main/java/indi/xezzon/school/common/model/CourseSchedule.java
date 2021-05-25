package indi.xezzon.school.common.model;

import indi.xezzon.school.common.constant.enums.AbWeekEnum;
import indi.xezzon.school.common.constant.enums.WeekdayEnum;
import lombok.*;
import lombok.experimental.Accessors;

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
public class CourseSchedule extends BaseEntity {
    /**
     * 课程ID
     */
    private Long courseId;

    /**
     * 开始周数
     */
    private Integer startWeek;

    /**
     * 结束周数
     */
    private Integer endWeek;

    /**
     * 单双周
     */
    private AbWeekEnum abWeek;

    /**
     * 周课时
     */
    private Integer classhourWeek;

    /**
     * 周几
     */
    private WeekdayEnum weekday;

    /**
     * 第几节课
     */
    private Integer tempo;

    /**
     * 课时
     */
    private Integer classhour;

    /**
     * 教室
     */
    private Room room;

    private static final long serialVersionUID = 1L;
}
