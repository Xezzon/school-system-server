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
public class Teacher implements Serializable {
    private Long id;

    /**
     * 教师姓名
     */
    private String name;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 简介
     */
    private String readme;

    private static final long serialVersionUID = 1L;
}
