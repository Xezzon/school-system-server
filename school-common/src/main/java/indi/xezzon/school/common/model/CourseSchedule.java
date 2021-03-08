package indi.xezzon.school.common.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author xezzon
 */
@Getter
@Setter
@ToString
public class CourseSchedule implements Serializable {

    private Integer startWeek;

    private Integer endWeek;

    private Integer abWeek;

    private Integer wk;

    private Integer tempo;

    private Integer classhour;

    private String building;

    private String doorplate;
}
