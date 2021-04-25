package indi.xezzon.school.common.model;

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
     * 单双周。0:非单双周；1:单周；2:双周；
     */
    private Integer abWeek;

    /**
     * 周几
     */
    private Integer wk;

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
    private Long addressId;

    /**
     * 教学楼
     */
    private String building;

    /**
     * 门牌号
     */
    private String doorplate;
}
